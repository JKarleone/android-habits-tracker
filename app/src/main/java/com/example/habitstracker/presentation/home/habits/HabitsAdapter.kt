package com.example.habitstracker.presentation.home.habits

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.Habit
import com.example.habitstracker.R
import com.example.habitstracker.presentation.home.Mapper

class HabitsAdapter(
    private var habits: MutableList<Habit>,
    private val clickListener: OnHabitItemListener,
    private val mapper: Mapper
) : RecyclerView.Adapter<HabitsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            inflater.inflate(R.layout.habit_item, parent, false),
            mapper,
            clickListener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(habits[position])

        holder.itemView.setOnClickListener {
            clickListener.onHabitItemClick(habits[position])
        }

        holder.itemView.setOnLongClickListener {
            clickListener.onHabitItemLongClick(habits[position])
        }
    }

    override fun getItemCount(): Int {
        return habits.size
    }

    fun setHabits(newListOfHabits: MutableList<Habit>) {
        val diffUtil = HabitsDiffUtil(habits, newListOfHabits)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        habits = newListOfHabits
        diffResults.dispatchUpdatesTo(this)
    }

    class ViewHolder(
        private val view: View,
        private val mapper: Mapper,
        private val listener: OnHabitItemListener
    ) : RecyclerView.ViewHolder(view) {

        private val name: TextView = view.findViewById(R.id.habitName)
        private val description: TextView = view.findViewById(R.id.habitDescription)
        private val color: ImageView = view.findViewById(R.id.selectedHabitColor)
        private val frequency: TextView = view.findViewById(R.id.habitFrequency)
        private val priority: TextView = view.findViewById(R.id.habitPriority)
        private val doneButton: Button = view.findViewById(R.id.buttonDone)

        fun bind(habit: Habit) {
            name.text = habit.name
            description.text = habit.description
            color.setColorFilter(habit.color)
            priority.text = habit.priority.toString()

            val countTimesString = view.resources.getQuantityString(R.plurals.plurals_frequency_times, habit.frequencyTimes, habit.frequencyTimes)
            val frequencyString = mapper.mapToString(habit.frequency)

            frequency.text = "$countTimesString $frequencyString"

            doneButton.setOnClickListener {
                listener.onHabitDoneClick(habit)
            }
        }

    }

    interface OnHabitItemListener {
        fun onHabitItemClick(habit: Habit)
        fun onHabitItemLongClick(habit: Habit): Boolean
        fun onHabitDoneClick(habit: Habit)
    }

}