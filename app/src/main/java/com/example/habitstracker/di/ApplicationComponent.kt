package com.example.habitstracker.di

import com.example.habitstracker.presentation.home.BottomSheetFragment
import com.example.habitstracker.presentation.home.HomeFragment
import com.example.habitstracker.presentation.home.habiteditor.HabitEditorFragment
import com.example.habitstracker.presentation.home.habits.HabitsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
//        RepositoryModule::class,
//        InteractorModule::class,
        RemoteModule::class,
        RoomModule::class,
        ContextModule::class,
        MapperModule::class,
        AppModule::class
    ]
)
interface ApplicationComponent {

    // Fragments
//    fun inject(habitEditorFragment: HabitEditorFragment)
//    fun inject(habitsFragment: HabitsFragment)
    fun inject(homeFragment: HomeFragment)
//    fun inject(bottomSheetFragment: BottomSheetFragment)

    fun habitSubcomponent(): HabitSubcomponent.Builder

}