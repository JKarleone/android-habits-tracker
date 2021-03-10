package com.example.habitstracker.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import com.example.habitstracker.utils.ColorPicker
import com.example.habitstracker.R
import com.example.habitstracker.databinding.ActivityHabitBinding
import com.example.habitstracker.models.Habit
import com.example.habitstracker.utils.HabitFrequency
import com.example.habitstracker.utils.HabitPriority
import com.example.habitstracker.utils.HabitType

class HabitActivity : AppCompatActivity(), ColorPicker.OnColorSquareItemListener {

    private lateinit var binding: ActivityHabitBinding
    private lateinit var prioritiesItems: List<String>
    private lateinit var intervalItems: List<String>
    private var position: Int? = null
    private lateinit var colorPicker: ColorPicker
    private var currentHabitColor: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHabitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val bundle = intent.extras
        val requestCode = bundle?.getInt(MainActivity.EXTRA_REQUEST_CODE) ?: -1

        setAdapters()
        colorPicker = ColorPicker(this, binding.habitsColorLinearLayout)
        currentHabitColor = ContextCompat.getColor(this, R.color.gray)

        when (requestCode) {
            MainActivity.EDIT_HABIT_REQUEST -> {
                binding.habitActivityHeader.text = resources.getString(R.string.header_edit_habit)
                binding.confirmHabitButton.text = resources.getString(R.string.habit_button_save)
                binding.confirmHabitButton.icon = ContextCompat.getDrawable(this, R.drawable.ic_edit)
                bundle?.let {
                    fillFieldsWithHabitData(it)
                    position = bundle.getInt(EXTRA_HABIT_POSITION)
                }
            }
            MainActivity.NEW_HABIT_REQUEST -> {
                binding.habitActivityHeader.text = resources.getString(R.string.header_new_habit)
                binding.confirmHabitButton.text = resources.getString(R.string.habit_button_add)
                binding.confirmHabitButton.icon = ContextCompat.getDrawable(this, R.drawable.ic_add)
                binding.selectedHabitColor.setColorFilter(currentHabitColor)
            }
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setAdapters()
        binding.editTextPriority.setText(savedInstanceState.getSerializable(EXTRA_HABIT_PRIORITY).toString())
        binding.editTextInterval.setText(savedInstanceState.getSerializable(EXTRA_HABIT_FREQUENCY).toString())
        binding.selectedHabitColor.setColorFilter(savedInstanceState.getInt(EXTRA_HABIT_COLOR, 0))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (binding.editTextPriority.text.toString().isNotEmpty())
            outState.putSerializable(EXTRA_HABIT_PRIORITY, HabitPriority.getHabitPriorityByString(binding.editTextPriority.text.toString()))
        if (binding.editTextInterval.text.toString().isNotEmpty())
            outState.putSerializable(EXTRA_HABIT_FREQUENCY, HabitFrequency.getHabitFrequencyByString(binding.editTextInterval.text.toString()))
        outState.putInt(EXTRA_HABIT_COLOR, currentHabitColor)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        setResult(RESULT_CANCELED)
    }

    private fun fillFieldsWithHabitData(bundle: Bundle) {
        val habit = bundle.getSerializable(EXTRA_HABIT) as Habit
        val (name, description, priority, type, frequencyTimes, frequency, color) = habit
        currentHabitColor = color

        binding.editTextName.setText(name)
        binding.editTextDescription.setText(description)
        binding.editTextPriority.setText(priority.toString(), false)
        binding.editTextTimesCount.setText(frequencyTimes.toString())
        binding.editTextInterval.setText(frequency.toString(), false)
        when(type) {
            HabitType.Good -> binding.radioGroupHabitType.check(R.id.radioButtonGoodHabit)
            HabitType.Bad -> binding.radioGroupHabitType.check(R.id.radioButtonBadHabit)
        }
        binding.selectedHabitColor.setColorFilter(currentHabitColor)
    }

    private fun setAdapters() {
        prioritiesItems = HabitPriority.values().map { resources.getString(it.resourceId) }
        val priorityAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, prioritiesItems)
        (binding.editTextPriority as? AutoCompleteTextView)?.setAdapter(priorityAdapter)

        intervalItems = HabitFrequency.values().map { resources.getString(it.resourceId) }
        val intervalAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, intervalItems)
        (binding.editTextInterval as? AutoCompleteTextView)?.setAdapter(intervalAdapter)
    }

    fun onSaveHabitButtonClicked(view: View) {
        if (isAllFieldsFilled()) {
            val data = Intent().apply {
                val name = binding.editTextName.text.toString()
                val description = binding.editTextDescription.text.toString()
                val priority = HabitPriority.getHabitPriorityByString(binding.editTextPriority.text.toString())
                val frequencyTimes = binding.editTextTimesCount.text.toString().toInt()
                val frequency = HabitFrequency.getHabitFrequencyByString(binding.editTextInterval.text.toString())
                val color = currentHabitColor

                val type = when(binding.radioGroupHabitType.checkedRadioButtonId) {
                    R.id.radioButtonGoodHabit -> HabitType.Good
                    else -> HabitType.Bad
                }
                val habit = Habit(name, description, priority!!, type, frequencyTimes, frequency!!,color)
                putExtra(EXTRA_HABIT, habit)
                putExtra(EXTRA_HABIT_POSITION, position)
            }
            setResult(RESULT_OK, data)
            finish()
        }
    }

    private fun isAllFieldsFilled(): Boolean {
        var isOk = true

        clearFocus()

        if (binding.editTextName.text.toString().isEmpty()) {
            isOk = false
            binding.inputLayoutName.error = resources.getString(R.string.error_fill_this_field)
        } else {
            binding.inputLayoutName.isErrorEnabled = false
        }

        if (binding.editTextDescription.text.toString().isEmpty()) {
            isOk = false
            binding.inputLayoutDescription.error = resources.getString(R.string.error_fill_this_field)
        } else {
            binding.inputLayoutDescription.isErrorEnabled = false
        }

        if (binding.editTextPriority.text.toString().isEmpty()) {
            isOk = false
            binding.inputLayoutPriority.error = resources.getString(R.string.error_make_a_choice)
        } else {
            binding.inputLayoutPriority.isErrorEnabled = false
        }

        if (binding.editTextTimesCount.text.toString().isEmpty()) {
            isOk = false
            binding.inputLayoutTimesCount.error = resources.getString(R.string.error_fill_this_field)
        } else {
            binding.inputLayoutTimesCount.isErrorEnabled = false
        }

        if (binding.editTextInterval.text.toString().isEmpty()) {
            isOk = false
            binding.inputLayoutInterval.error = resources.getString(R.string.error_make_a_choice)
        } else {
            binding.inputLayoutInterval.isErrorEnabled = false
        }

        if (binding.radioGroupHabitType.checkedRadioButtonId == View.NO_ID) {
            isOk = false
            binding.radioGroupError.setText(R.string.error_make_a_choice)
            binding.radioGroupError.visibility = View.VISIBLE
        } else {
            binding.radioGroupError.visibility = View.GONE
        }

        if (currentHabitColor == ContextCompat.getColor(this, R.color.gray)) {
            isOk = false
            binding.selectedHabitColorError.setText(R.string.error_make_a_choice)
            binding.selectedHabitColorError.visibility = View.VISIBLE
        } else {
            binding.selectedHabitColorError.visibility = View.GONE
        }

        return isOk
    }

    private fun clearFocus() {
        window.decorView.clearFocus()
    }

    override fun onColorSquareItemClick(view: View) {
        currentHabitColor = view.tag.toString().toInt()
        binding.selectedHabitColor.setColorFilter(currentHabitColor)
    }

    companion object {
        const val EXTRA_HABIT = "extra_habit"
        const val EXTRA_HABIT_POSITION = "extra_habit_position"
        private const val EXTRA_HABIT_PRIORITY = "extra_habit_priority"
        private const val EXTRA_HABIT_FREQUENCY = "extra_habit_frequency"
        private const val EXTRA_HABIT_COLOR = "extra_habit_color"
    }

}