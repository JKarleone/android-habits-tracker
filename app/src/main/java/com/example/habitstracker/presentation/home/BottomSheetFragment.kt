package com.example.habitstracker.presentation.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.habitstracker.R
import com.example.habitstracker.databinding.FragmentBottomSheetBinding

class BottomSheetFragment : Fragment() {

    private var _binding: FragmentBottomSheetBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HabitsViewModel by viewModels(
        ownerProducer = { requireParentFragment() }
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentBottomSheetBinding.inflate(inflater, container, false)

        configureSearchEditText()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    private fun configureSearchEditText() {
        binding.editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.searchSubstring.value = s?.toString() ?: ""
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

}