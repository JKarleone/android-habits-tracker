package com.example.habitstracker.di

import com.example.habitstracker.presentation.home.BottomSheetFragment
import com.example.habitstracker.presentation.home.habiteditor.HabitEditorFragment
import com.example.habitstracker.presentation.home.habits.HabitsFragment
import dagger.Subcomponent

@ActivityScope
@Subcomponent(
    modules = [
        InteractorModule::class,
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
    fun inject(habitEditorFragment: HabitEditorFragment)

}