package com.example.habitstracker.presentation.home.habits

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.Habit
import com.example.habitstracker.App
import com.example.habitstracker.databinding.FragmentHabitsBinding
import com.example.habitstracker.presentation.home.*
import javax.inject.Inject

class HabitsFragment : Fragment(), HabitsAdapter.OnHabitItemListener {

    private var _binding: FragmentHabitsBinding? = null
    private val binding get() = _binding!!

    private var habitType: com.example.domain.utils.HabitType? = null
    private lateinit var habitsAdapter: HabitsAdapter
    private lateinit var layoutManager: LinearLayoutManager

    @Inject
    lateinit var mapper: Mapper

//    private val viewModel: HabitsViewModel by viewModels(
//            ownerProducer = { requireParentFragment() }
//    )
//    @Inject
//    lateinit var viewModel: HabitsViewModel

    @Inject
    lateinit var providerFactory: HabitsViewModelFactory

    private lateinit var viewModel: HabitsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (requireActivity().application as App).applicationComponent.inject(this)

        viewModel = ViewModelProvider(this, providerFactory).get(HabitsViewModel::class.java)

        arguments?.let {
            habitType = it.getSerializable(ARG_HABIT_TYPE) as? com.example.domain.utils.HabitType
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHabitsBinding.inflate(layoutInflater, container, false)

        initRecyclerView()

        setObservers()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    private fun initRecyclerView() {
        habitsAdapter = HabitsAdapter(mutableListOf(), this, mapper)
        binding.habitsRecyclerView.adapter = habitsAdapter
        layoutManager = LinearLayoutManager(requireContext())
        binding.habitsRecyclerView.layoutManager = layoutManager
        binding.habitsRecyclerView.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
    }

    private fun setObservers() {

        val habitsObserver = Observer<List<Habit>> {
            habitsAdapter.setHabits(it.toMutableList())
        }

        habitType?.let {
            if (habitType == com.example.domain.utils.HabitType.Good)
                viewModel.goodHabits.observe(viewLifecycleOwner, habitsObserver)
            else
                viewModel.badHabits.observe(viewLifecycleOwner, habitsObserver)
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
        fun newInstance(habitType: com.example.domain.utils.HabitType) =
            HabitsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_HABIT_TYPE, habitType)
                }
            }

        private const val ARG_HABIT_TYPE = "habit_type"
        private const val TAG = "FragmentHabits"
    }
}