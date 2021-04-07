package com.example.habitstracker

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.habitstracker.adapters.HabitAdapter
import com.example.habitstracker.databinding.FragmentHabitsBinding
import com.example.habitstracker.models.Habit
import com.example.habitstracker.utils.HabitType
import java.io.Serializable

private const val ARG_HABIT_TYPE = "habit_type"
private const val ARG_ITEM_CLICK_LISTENER = "item_click_listener"

class HabitsFragment : Fragment() {

    private var _binding: FragmentHabitsBinding? = null
    private val binding get() = _binding!!

    private var habitType: HabitType? = null
    private lateinit var habitAdapter: HabitAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private var habits = ArrayList<Habit>()

    private var itemClickListener: HabitAdapter.OnHabitItemListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            habitType = it.getSerializable(ARG_HABIT_TYPE) as? HabitType
            itemClickListener = it.getSerializable(ARG_ITEM_CLICK_LISTENER) as? HabitAdapter.OnHabitItemListener
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
        habitAdapter = HabitAdapter(habits, itemClickListener!!)
        binding.habitsRecyclerView.adapter = habitAdapter
        layoutManager = LinearLayoutManager(requireContext())
        binding.habitsRecyclerView.layoutManager = layoutManager
        binding.habitsRecyclerView.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
    }

    fun updateRecyclerViewData() {
        habitType?.let {
            habits = Data.getHabitsByType(it)
            habitAdapter.setHabits(habits)
            habitAdapter.notifyDataSetChanged()
        }
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
        fun newInstance(habitType: HabitType, itemClickListener: HabitAdapter.OnHabitItemListener) =
            HabitsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_HABIT_TYPE, habitType)
                    putSerializable(ARG_ITEM_CLICK_LISTENER, itemClickListener as Serializable)
                }
            }

        private const val EXTRA_HABITS = "extra_habits"
        private const val TAG = "FragmentHabits"
    }
}