package com.example.habitstracker.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.HabitInteractor
import com.example.habitstracker.utils.HabitToastHelper
import javax.inject.Inject

class HabitsViewModelFactory @Inject constructor (
    private val interactor: HabitInteractor,
    private val toastHelper: HabitToastHelper
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HabitsViewModel::class.java)) {
            return HabitsViewModel(interactor, toastHelper) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}