package com.example.habitstracker.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.habitstracker.App
import com.example.habitstracker.R
import com.example.habitstracker.databinding.FragmentHomeBinding
import com.example.habitstracker.utils.Util.Companion.dpToPx
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewPagerAdapter: HabitsViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        viewPagerAdapter = HabitsViewPagerAdapter(childFragmentManager, lifecycle)
        binding.habitsList.habitsViewPager.offscreenPageLimit = 2
        binding.habitsList.habitsViewPager.adapter = viewPagerAdapter
        TabLayoutMediator(binding.habitsList.habitsTabLayout, binding.habitsList.habitsViewPager) { tab, position ->
            tab.text = when (position) {
                0 -> App.applicationContext().getString(R.string.view_pager_good_habits_header)
                else -> App.applicationContext().getString(R.string.view_pager_bad_habits_header)
            }
        }.attach()

        binding.addNewHabitButton.setOnClickListener(this::onAddNewHabitButtonClicked)

        initBottomSheet()

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

    private fun initBottomSheet() {
        BottomSheetBehavior.from(binding.bottomSheet).apply {
            peekHeight = 50.dpToPx
            state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }

    companion object {

        private const val TAG = "FragmentHome"

    }

}