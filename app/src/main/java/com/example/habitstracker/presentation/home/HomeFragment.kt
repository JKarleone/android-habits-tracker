package com.example.habitstracker.presentation.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.habitstracker.App
import com.example.habitstracker.R
import com.example.habitstracker.databinding.FragmentHomeBinding
import com.example.habitstracker.utils.ResourceManager
import com.example.habitstracker.utils.Util.Companion.dpToPx
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.tabs.TabLayoutMediator
import javax.inject.Inject

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var resourceManager: ResourceManager

    private lateinit var viewPagerAdapter: HabitsViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (requireActivity().application as App).applicationComponent.inject(this)
    }

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
                0 -> resourceManager.getResourceString(R.string.view_pager_good_habits_header)
                else -> resourceManager.getResourceString(R.string.view_pager_bad_habits_header)
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
        val bottomSheet = BottomSheetBehavior.from(binding.bottomSheet).apply {
            peekHeight = 60.dpToPx
            state = BottomSheetBehavior.STATE_COLLAPSED
        }
        bottomSheet.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    val viewInFocus = activity?.currentFocus
                    val inputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                    // Hide keyboard
                    inputMethodManager?.hideSoftInputFromWindow(viewInFocus?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
                    // Clear focus of last input field
                    viewInFocus?.clearFocus()
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
            }
        })
    }

    companion object {

        private const val TAG = "FragmentHome"

    }

}