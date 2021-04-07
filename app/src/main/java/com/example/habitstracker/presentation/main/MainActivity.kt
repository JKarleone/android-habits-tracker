package com.example.habitstracker.presentation.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.habitstracker.R
import com.example.habitstracker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController
    private lateinit var appBarConfig: AppBarConfiguration

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = findNavController(R.id.fragment)
        appBarConfig = AppBarConfiguration(
            setOf(R.id.homeFragment, R.id.appInfoFragment),
//                navController.graph,
            binding.drawerLayout
        )
        binding.navigationView.setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBarConfig)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfig) || super.onSupportNavigateUp()
    }

    //    private lateinit var binding: ActivityMainBinding
//    private var habits = ArrayList<Habit>()
//    private lateinit var habitAdapter: HabitAdapter
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        initRecyclerView()
//    }

//    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
//        super.onRestoreInstanceState(savedInstanceState)
//
//        habits = savedInstanceState.getSerializable(EXTRA_HABITS) as ArrayList<Habit>
//        (binding.habitsRecyclerView.adapter as? HabitAdapter)?.setHabits(habits)
//        binding.habitsRecyclerView.adapter?.notifyDataSetChanged()
//    }
//
//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//
//        outState.putSerializable(EXTRA_HABITS, habits)
//    }
//
//    private fun initRecyclerView() {
//        habitAdapter = HabitAdapter(habits, this)
//        binding.habitsRecyclerView.adapter = habitAdapter
//        val layoutManager = LinearLayoutManager(this)
//        binding.habitsRecyclerView.layoutManager = layoutManager
//        binding.habitsRecyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
//    }
//
//    fun onAddNewHabitButtonClicked(view: View) {
//        val intent = HabitActivity.getIntent(this, NEW_HABIT_REQUEST)
//        startActivityForResult(intent, NEW_HABIT_REQUEST)
//    }
//
//    override fun onHabitItemClick(position: Int) {
//        val habit = habits[position]
//        val intent = HabitActivity.getIntent(this, EDIT_HABIT_REQUEST, habit, position)
//        startActivityForResult(intent, EDIT_HABIT_REQUEST)
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        val bundle = data?.extras
//
//        if (resultCode != RESULT_OK) {
//            return
//        }
//
//        bundle?.apply {
//            val habit = getSerializable(HabitActivity.EXTRA_HABIT) as Habit
//
//            when {
//                requestCode == NEW_HABIT_REQUEST && resultCode == RESULT_OK -> {
//                    habits.add(habit)
//                    binding.habitsRecyclerView.adapter?.notifyItemInserted(habits.size - 1)
//                }
//
//                requestCode == EDIT_HABIT_REQUEST && resultCode == RESULT_OK -> {
//                    val position = getInt(HabitActivity.EXTRA_HABIT_POSITION)
//                    habits.removeAt(position)
//                    habits.add(position, habit)
//                    binding.habitsRecyclerView.adapter?.notifyItemChanged(position)
//                }
//            }
//        }
//    }
//
//    companion object {
//        const val NEW_HABIT_REQUEST = 11
//        const val EDIT_HABIT_REQUEST = 13
//        const val EXTRA_REQUEST_CODE = "extra_request_code"
//        private const val EXTRA_HABITS = "extra_habits"
//    }
}