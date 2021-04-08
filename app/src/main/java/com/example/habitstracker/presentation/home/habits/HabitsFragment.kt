package com.example.habitstracker.presentation.home.habits

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.habitstracker.data.Data
import com.example.habitstracker.databinding.FragmentHabitsBinding
import com.example.habitstracker.domain.model.Habit
import com.example.habitstracker.presentation.home.HomeFragment
import com.example.habitstracker.presentation.home.HomeFragmentDirections
import com.example.habitstracker.utils.HabitType

private const val ARG_HABIT_TYPE = "habit_type"

class HabitsFragment : Fragment(), HabitsAdapter.OnHabitItemListener {

    private var _binding: FragmentHabitsBinding? = null
    private val binding get() = _binding!!

    private var habitType: HabitType? = null
    private lateinit var habitsAdapter: HabitsAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private var habits = ArrayList<Habit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            habitType = it.getSerializable(ARG_HABIT_TYPE) as? HabitType
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHabitsBinding.inflate(layoutInflater, container, false)

        initRecyclerView()
        updateRecyclerViewData()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    private fun initRecyclerView() {
        habitsAdapter = HabitsAdapter(habits, this)
        binding.habitsRecyclerView.adapter = habitsAdapter
        layoutManager = LinearLayoutManager(requireContext())
        binding.habitsRecyclerView.layoutManager = layoutManager
        binding.habitsRecyclerView.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
    }

    fun updateRecyclerViewData() {
        habitType?.let {
            habits = Data.getHabitsByType(it)
            habitsAdapter.setHabits(habits)
        }
    }

    override fun onHabitItemClick(habit: Habit) {
        val fragment = parentFragment as HomeFragment
        val navController = fragment.findNavController()
        val action = HomeFragmentDirections.actionHomeFragmentToHabitEditorFragment(habit)
        navController.navigate(action)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param habitType Choose habit type of habits.
         * @return A new instance of fragment HabitsFragment.
         */
        @JvmStatic
        fun newInstance(habitType: HabitType) =
            HabitsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_HABIT_TYPE, habitType)
                }
            }

        private const val TAG = "FragmentHabits"
    }
}