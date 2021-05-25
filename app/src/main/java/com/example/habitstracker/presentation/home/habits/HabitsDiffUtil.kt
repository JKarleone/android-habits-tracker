package com.example.habitstracker.presentation.home.habits

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.Habit

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
                oldList[oldItemPosition].description == newList[newItemPosition].description &&
                oldList[oldItemPosition].priority == newList[newItemPosition].priority &&
                oldList[oldItemPosition].type == newList[newItemPosition].type &&
                oldList[oldItemPosition].frequencyTimes == newList[newItemPosition].frequencyTimes &&
                oldList[oldItemPosition].frequency == newList[newItemPosition].frequency &&
                oldList[oldItemPosition].color == newList[newItemPosition].color
    }

}