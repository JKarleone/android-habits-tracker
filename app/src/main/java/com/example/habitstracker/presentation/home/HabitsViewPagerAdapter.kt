package com.example.habitstracker.presentation.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.habitstracker.presentation.home.habits.HabitAdapter
import com.example.habitstracker.presentation.home.habits.HabitsFragment
import com.example.habitstracker.utils.HabitType

class HabitsViewPagerAdapter (
    fm: FragmentManager,
    lifecycle: Lifecycle,
    private val itemClickListener: HabitAdapter.OnHabitItemListener
) : FragmentStateAdapter(fm, lifecycle) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> HabitsFragment.newInstance(HabitType.Good, itemClickListener)
        else -> HabitsFragment.newInstance(HabitType.Bad, itemClickListener)
    }

}