package com.example.habitstracker.di

import com.example.habitstracker.presentation.home.BottomSheetFragment
import com.example.habitstracker.presentation.home.habits.HabitsFragment
import dagger.Subcomponent

@ActivityScope
@Subcomponent(
    modules = [
        RepositoryModule::class
    ]
)
interface HabitSubcomponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): HabitSubcomponent
    }

    fun inject(habitsFragment: HabitsFragment)
    fun inject(bottomSheetFragment: BottomSheetFragment)

}