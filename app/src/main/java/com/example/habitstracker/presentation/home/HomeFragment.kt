package com.example.habitstracker.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.habitstracker.App
import com.example.habitstracker.R
import com.example.habitstracker.data.Data
import com.example.habitstracker.databinding.FragmentHomeBinding
import com.example.habitstracker.domain.model.Habit
import com.example.habitstracker.presentation.home.habiteditor.HabitEditorFragment
import com.example.habitstracker.presentation.home.habits.HabitAdapter
import com.example.habitstracker.presentation.home.habits.HabitsFragment
import com.example.habitstracker.utils.HabitType
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment(), HabitAdapter.OnHabitItemListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewPagerAdapter: HabitsViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        viewPagerAdapter = HabitsViewPagerAdapter(childFragmentManager, lifecycle, this)
        binding.habitsViewPager.offscreenPageLimit = 2
        binding.habitsViewPager.adapter = viewPagerAdapter
        TabLayoutMediator(binding.habitsTabLayout, binding.habitsViewPager) { tab, position ->
            tab.text = when (position) {
                0 -> App.applicationContext().getString(R.string.view_pager_good_habits_header)
                else -> App.applicationContext().getString(R.string.view_pager_bad_habits_header)
            }
        }.attach()

        binding.addNewHabitButton.setOnClickListener(this::onAddNewHabitButtonClicked)

        setFragmentResultListeners()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    private fun onAddNewHabitButtonClicked(view: View) {
        val navController = findNavController()
        val action = HomeFragmentDirections.actionHomeFragmentToHabitEditorFragment()
        navController.navigate(action)
    }

    private fun setFragmentResultListeners() {

        setFragmentResultListener(HabitEditorFragment.REQUEST_KEY_NEW_HABIT) { _, bundle ->
            val newHabit = bundle.getSerializable(HabitEditorFragment.EXTRA_HABIT) as Habit

            Data.addNewHabit(newHabit)
            val fragment = getHabitsFragmentByHabitType(newHabit.type)
            fragment?.updateRecyclerViewData()
        }

        setFragmentResultListener(HabitEditorFragment.REQUEST_KEY_EDIT_HABIT) { _, bundle ->
            val habit = bundle.getSerializable(HabitEditorFragment.EXTRA_HABIT) as Habit

            Data.updateHabit(habit)

            val fragment1 = getHabitsFragmentByHabitType(HabitType.Good)
            val fragment2 = getHabitsFragmentByHabitType(HabitType.Bad)
            fragment1?.updateRecyclerViewData()
            fragment2?.updateRecyclerViewData()
        }

    }

    private fun getHabitsFragmentByHabitType(habitType: HabitType) : HabitsFragment? {
        val fragmentTag = if (habitType == HabitType.Good) "f0" else "f1"
        return childFragmentManager.findFragmentByTag(fragmentTag) as? HabitsFragment
    }

    override fun onHabitItemClick(habit: Habit) {
        val navController = findNavController()
        val action = HomeFragmentDirections.actionHomeFragmentToHabitEditorFragment(habit)
        navController.navigate(action)
    }

    companion object {

        private const val TAG = "FragmentHome"

    }

}