package com.example.habitstracker.presentation.home

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.habitstracker.App
import com.example.habitstracker.R
import com.example.habitstracker.databinding.FragmentBottomSheetBinding
import com.example.habitstracker.utils.SortField
import javax.inject.Inject

class BottomSheetFragment : Fragment() {

    private var _binding: FragmentBottomSheetBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var providerFactory: HabitsViewModelFactory

    lateinit var viewModel: HabitsViewModel

    private var lastCheckedChipView = R.id.chipName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (requireActivity().application as App).applicationComponent.inject(this)

        viewModel = ViewModelProvider(requireParentFragment(), providerFactory).get(HabitsViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_bottom_sheet, container, false)
        binding.viewModel = viewModel

        setSortOrderConfig()
        setChipChangeListener()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    private fun setSortOrderConfig() {
        binding.imageButtonSortByAscending.setOnClickListener(this::onImageButtonSortOrderClicked)
        binding.imageButtonSortByDescending.setOnClickListener(this::onImageButtonSortOrderClicked)

        viewModel.sortByAscending.observe(viewLifecycleOwner, {
            val primaryColor = TypedValue()
            requireActivity().theme.resolveAttribute(R.attr.colorPrimary, primaryColor, true)
            val disabledColor = ContextCompat.getColor(requireContext(), R.color.gray_700)

            ImageViewCompat.setImageTintList(
                binding.imageButtonSortByAscending,
                ColorStateList.valueOf(if (it) primaryColor.data else disabledColor)
            )
            ImageViewCompat.setImageTintList(
                binding.imageButtonSortByDescending,
                ColorStateList.valueOf(if (!it) primaryColor.data else disabledColor)
            )
        })
    }

    private fun setChipChangeListener() {
        binding.chipGroupSortByField.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.chipName -> {
                    viewModel.sortField.value = SortField.NAME
                    lastCheckedChipView = R.id.chipName
                }
                R.id.chipPriority -> {
                    viewModel.sortField.value = SortField.PRIORITY
                    lastCheckedChipView = R.id.chipPriority
                }
                R.id.chipColor -> {
                    viewModel.sortField.value = SortField.COLOR
                    lastCheckedChipView = R.id.chipColor
                }
                R.id.chipFrequency -> {
                    viewModel.sortField.value = SortField.FREQUENCY
                    lastCheckedChipView = R.id.chipFrequency
                }
                else -> {
                    group.check(lastCheckedChipView)
                }
            }
        }
    }

    private fun onImageButtonSortOrderClicked(view: View) {
        when (view.id) {
            R.id.imageButtonSortByAscending -> viewModel.sortByAscending.value = true
            R.id.imageButtonSortByDescending -> viewModel.sortByAscending.value = false
        }
    }

    companion object {

        private const val TAG = "FragmentBottomSheet"

    }

}