package com.example.habitstracker.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.habitstracker.adapters.HabitAdapter
import com.example.habitstracker.databinding.ActivityMainBinding
import com.example.habitstracker.models.Habit
import com.example.habitstracker.utils.HabitColor
import com.example.habitstracker.utils.HabitFrequency
import com.example.habitstracker.utils.HabitPriority
import com.example.habitstracker.utils.HabitType

class MainActivity : AppCompatActivity(), HabitAdapter.OnHabitItemListener {

    private lateinit var binding: ActivityMainBinding
    private var habits = ArrayList<Habit>()
    private lateinit var habitAdapter: HabitAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        habits.add(Habit("Smoking", "Smoke a lot", HabitPriority.High, HabitType.Bad, 2, HabitFrequency.EveryWeek, HabitColor.BlueLight.getColor()))
        habits.add(Habit("Drink water", "Just Do It", HabitPriority.Medium, HabitType.Good, 5, HabitFrequency.EveryDay, HabitColor.DeepOrange.getColor()))

        initRecyclerView()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        habits = savedInstanceState.getSerializable(EXTRA_HABITS) as ArrayList<Habit>
        (binding.habitsRecyclerView.adapter as? HabitAdapter)?.setHabits(habits)
        binding.habitsRecyclerView.adapter?.notifyDataSetChanged()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putSerializable(EXTRA_HABITS, habits)
    }

    private fun initRecyclerView() {
        habitAdapter = HabitAdapter(habits, this)
        binding.habitsRecyclerView.adapter = habitAdapter
        val layoutManager = LinearLayoutManager(this)
        binding.habitsRecyclerView.layoutManager = layoutManager
        binding.habitsRecyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }

    fun onAddNewHabitButtonClicked(view: View) {
        val intent = Intent(this, HabitActivity::class.java)
        intent.putExtra(EXTRA_REQUEST_CODE, NEW_HABIT_REQUEST)
        startActivityForResult(intent, NEW_HABIT_REQUEST)
    }

    override fun onHabitItemClick(position: Int) {
        val habit = habits[position]
        val intent = Intent(this, HabitActivity::class.java).apply {
            putExtra(EXTRA_REQUEST_CODE, EDIT_HABIT_REQUEST)
            putExtra(HabitActivity.EXTRA_HABIT, habit)
            putExtra(HabitActivity.EXTRA_HABIT_POSITION, position)
        }
        startActivityForResult(intent, EDIT_HABIT_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val bundle = data?.extras

        if (resultCode != RESULT_OK) {
            return
        }

        bundle?.apply {
            val habit = getSerializable(HabitActivity.EXTRA_HABIT) as Habit

            when {
                requestCode == NEW_HABIT_REQUEST && resultCode == RESULT_OK -> {
                    habits.add(habit)
                    binding.habitsRecyclerView.adapter?.notifyItemInserted(habits.size - 1)
                }

                requestCode == EDIT_HABIT_REQUEST && resultCode == RESULT_OK -> {
                    val position = getInt(HabitActivity.EXTRA_HABIT_POSITION)
                    habits.removeAt(position)
                    habits.add(position, habit)
                    binding.habitsRecyclerView.adapter?.notifyItemChanged(position)
                }
            }
        }
    }

    companion object {
        const val NEW_HABIT_REQUEST = 11
        const val EDIT_HABIT_REQUEST = 13
        const val EXTRA_REQUEST_CODE = "extra_request_code"
        private const val EXTRA_HABITS = "extra_habits"
    }
}