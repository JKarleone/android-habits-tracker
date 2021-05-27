package com.example.habitstracker.presentation.home.habiteditor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.HabitInteractor
import javax.inject.Inject

class HabitEditorViewModelFactory @Inject constructor (
    private val interactor: HabitInteractor
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HabitEditorViewModel::class.java)) {
            return HabitEditorViewModel(interactor) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}