package com.example.habitstracker.presentation.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.domain.utils.HabitType
import com.example.habitstracker.presentation.home.habits.HabitsFragment

class HabitsViewPagerAdapter (
    fm: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fm, lifecycle) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> HabitsFragment.newInstance(HabitType.Good)
        else -> HabitsFragment.newInstance(HabitType.Bad)
    }

}