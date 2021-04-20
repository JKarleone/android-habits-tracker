package com.example.habitstracker.utils

import com.example.habitstracker.data.entity.Habit

object SortUtil {

    fun ArrayList<Habit>.getSortedList(sortField: SortField, byAscending: Boolean = true): ArrayList<Habit> {
        return if (byAscending)
            this.getSortedListByAscending(sortField)
        else
            this.getSortedListByDescending(sortField)
    }

    private fun ArrayList<Habit>.getSortedListByAscending(sortField: SortField): ArrayList<Habit> {
        val list = this
        when (sortField) {
            SortField.NAME -> list.sortBy { it.name }
            SortField.PRIORITY -> list.sortBy { it.priority }
            SortField.COLOR -> list.sortBy { it.color }
            else -> list.sortWith(compareByDescending<Habit> { it.frequency }.thenBy { it.frequencyTimes })
        }
        return list
    }

    private fun ArrayList<Habit>.getSortedListByDescending(sortField: SortField): ArrayList<Habit> {
        val list = this
        when (sortField) {
            SortField.NAME -> list.sortByDescending { it.name }
            SortField.PRIORITY -> list.sortByDescending { it.priority }
            SortField.COLOR -> list.sortByDescending { it.color }
            else -> list.sortWith(compareBy<Habit> { it.frequency }.thenByDescending { it.frequencyTimes })
        }
        return list
    }

}