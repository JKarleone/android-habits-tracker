package com.example.habitstracker.presentation.home.habits

import androidx.recyclerview.widget.DiffUtil
import com.example.habitstracker.domain.model.Habit

class HabitsDiffUtil(
    private val oldList: List<Habit>,
    private val newList: List<Habit>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return  oldList[oldItemPosition].name == newList[newItemPosition].name &&
                oldList[oldItemPosition].description == newList[oldItemPosition].description &&
                oldList[oldItemPosition].priority == newList[oldItemPosition].priority &&
                oldList[oldItemPosition].type == newList[oldItemPosition].type &&
                oldList[oldItemPosition].frequencyTimes == newList[oldItemPosition].frequencyTimes &&
                oldList[oldItemPosition].frequency == newList[oldItemPosition].frequency &&
                oldList[oldItemPosition].color == newList[oldItemPosition].color
    }

}