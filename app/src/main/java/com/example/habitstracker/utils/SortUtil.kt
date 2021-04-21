package com.example.habitstracker.utils

import com.example.habitstracker.data.entity.Habit

object SortUtil {

    fun MutableList<Habit>.getSortedList(sortField: SortField, byAscending: Boolean = true): MutableList<Habit> {
        return if (byAscending)
            this.getSortedListByAscending(sortField)
        else
            this.getSortedListByAscending(sortField).reversed().toMutableList()
    }

    private fun MutableList<Habit>.getSortedListByAscending(sortField: SortField): MutableList<Habit> {
        val list = this
        when (sortField) {
            SortField.NAME -> list.sortBy { it.name }
            SortField.PRIORITY -> list.sortBy { it.priority }
            SortField.COLOR -> list.sortBy { it.color }
            else -> list.sortWith(compareByDescending<Habit> { it.frequency }.thenBy { it.frequencyTimes })
        }
        return list
    }

}