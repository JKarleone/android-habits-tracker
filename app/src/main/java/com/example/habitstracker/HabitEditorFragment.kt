package com.example.habitstracker

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.habitstracker.activities.MainActivity
import com.example.habitstracker.databinding.FragmentHabitEditorBinding
import com.example.habitstracker.models.Habit
import com.example.habitstracker.utils.ColorPicker
import com.example.habitstracker.utils.HabitFrequency
import com.example.habitstracker.utils.HabitPriority
import com.example.habitstracker.utils.HabitType
import com.google.android.material.textfield.TextInputLayout

class HabitEditorFragment : Fragment(), ColorPicker.OnColorSquareItemListener {

    private var _binding: FragmentHabitEditorBinding? = null
    private val binding get() = _binding!!

    private val args: HabitEditorFragmentArgs by navArgs()
    private var habit: Habit? = null
    private var id: Int? = null

    private lateinit var prioritiesItems: List<String>
    private lateinit var intervalItems: List<String>

    private lateinit var colorPicker: ColorPicker
    private var currentHabitColor: Int = 0

    private lateinit var requestKey: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHabitEditorBinding.inflate(layoutInflater, container, false)

        getArgs()
        setAdapters()
        requestKey = if (habit == null) REQUEST_KEY_NEW_HABIT else REQUEST_KEY_EDIT_HABIT

        colorPicker = ColorPicker(this, binding.habitsColorLinearLayout)
        currentHabitColor = ContextCompat.getColor(requireContext(), R.color.gray)

        fillForm()

        binding.confirmHabitButton.setOnClickListener(this::onSaveHabitButtonClicked)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    private fun getArgs() {
        habit = args.habit
        id = habit?.id
    }

    private fun setAdapters() {
        prioritiesItems = HabitPriority.values().map { resources.getString(it.resourceId) }
        val priorityAdapter = ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, prioritiesItems)
        (binding.editTextPriority as? AutoCompleteTextView)?.setAdapter(priorityAdapter)

        intervalItems = HabitFrequency.values().map { resources.getString(it.resourceId) }
        val intervalAdapter = ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, intervalItems)
        (binding.editTextInterval as? AutoCompleteTextView)?.setAdapter(intervalAdapter)
    }

    private fun fillForm() {
        if (habit != null) {
            (activity as MainActivity).supportActionBar?.title = resources.getString(R.string.header_edit_habit)
            binding.confirmHabitButton.text = resources.getString(R.string.habit_button_save)
            binding.confirmHabitButton.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_edit)
            habit?.let { fillFieldsWithHabitData(it) }
        }
        else {
            (activity as MainActivity).supportActionBar?.title = resources.getString(R.string.header_new_habit)
            binding.confirmHabitButton.text = resources.getString(R.string.habit_button_add)
            binding.confirmHabitButton.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_add)
            binding.selectedHabitColor.setColorFilter(currentHabitColor)
        }
    }

    private fun fillFieldsWithHabitData(habit: Habit) {
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
        setSelectedColorToView()
    }

    override fun onColorSquareItemClick(view: View) {
        currentHabitColor = view.tag.toString().toInt()
        setSelectedColorToView()
    }

    private fun setSelectedColorToView() {
        binding.selectedHabitColor.setColorFilter(currentHabitColor)
        binding.selectedHabitColorRGB.text = getSelectedColorRGB()
        val hsv = getSelectedColorHSV()
        binding.selectedHabitColorHSV.text = "${hsv[0]}°, ${hsv[1]}, ${hsv[2]}"
    }

    private fun getSelectedColorRGB(): String {
        return String.format("#%06X", (0xFFFFFF and currentHabitColor))
    }

    private fun getSelectedColorHSV(): FloatArray {
        val hsv = FloatArray(3)
        Color.colorToHSV(currentHabitColor, hsv)
        return hsv
    }

    private fun onSaveHabitButtonClicked(view: View) {
        if (isAllFieldsFilled()) {
            setDataToHabit()
            setFragmentResult(requestKey, Bundle().apply {
                putSerializable(EXTRA_HABIT, habit)
            })
            val navController = findNavController()
            navController.popBackStack()
        }
    }

    private fun isAllFieldsFilled(): Boolean {
        clearFocus()

        val inputViews = listOf(
                InputView(binding.editTextName, binding.inputLayoutName, R.string.error_fill_this_field),
                InputView(binding.editTextDescription, binding.inputLayoutDescription, R.string.error_fill_this_field),
                InputView(binding.editTextPriority, binding.inputLayoutPriority, R.string.error_make_a_choice),
                InputView(binding.editTextTimesCount, binding.inputLayoutTimesCount, R.string.error_fill_this_field),
                InputView(binding.editTextInterval, binding.inputLayoutInterval, R.string.error_make_a_choice)
        )

        return isNoErrorInFields(inputViews).and(isTypeSelected().and(isColorSelected()))
    }

    private fun clearFocus() {
        val viewInFocus = activity?.currentFocus
        val inputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        // Hide keyboard
        inputMethodManager?.hideSoftInputFromWindow(viewInFocus?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        // Clear focus of last input field
        viewInFocus?.clearFocus()
    }

    private fun isTypeSelected(): Boolean {
        var result = true
        if (binding.radioGroupHabitType.checkedRadioButtonId == View.NO_ID) {
            result = false
            binding.radioGroupError.setText(R.string.error_make_a_choice)
            binding.radioGroupError.visibility = View.VISIBLE
        } else
            binding.radioGroupError.visibility = View.GONE

        return result
    }

    private fun isColorSelected(): Boolean {
        val result = currentHabitColor != ContextCompat.getColor(requireContext(), R.color.gray)
        if (!result) {
            binding.selectedHabitColorError.setText(R.string.error_make_a_choice)
            binding.selectedHabitColorError.visibility = View.VISIBLE
        } else {
            binding.selectedHabitColorError.visibility = View.GONE
        }
        return result
    }

    private fun isNoErrorInFields(inputViews: List<InputView>): Boolean {
        var result = true

        for (view in inputViews) {
            if (view.inputView.text.toString().isEmpty()) {
                result = false
                view.errorLayout.error = getString(view.errorStringResource)
            } else
                view.errorLayout.isErrorEnabled = false
        }

        return result
    }

    private fun setDataToHabit() {
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

        habit = if (id != null)
            Habit(name, description, priority!!, type, frequencyTimes, frequency!!,color, id!!)
        else
            Habit(name, description, priority!!, type, frequencyTimes, frequency!!,color)
    }

    private data class InputView(
        val inputView: EditText,
        val errorLayout: TextInputLayout,
        val errorStringResource: Int
    )

    companion object {

        const val REQUEST_KEY_EDIT_HABIT = "request_key_edit_habit"
        const val REQUEST_KEY_NEW_HABIT = "request_key_new_habit"

        const val EXTRA_HABIT = "extra_habit"

        private const val TAG = "FragmentHabitEditor"

    }

}