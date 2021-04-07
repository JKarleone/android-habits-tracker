package com.example.habitstracker.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.habitstracker.R
import com.example.habitstracker.models.Habit

class HabitAdapter(private var habits: MutableList<Habit>,
                   private val itemClickListener: HabitAdapter.OnHabitItemListener) : RecyclerView.Adapter<HabitAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.habit_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(habits[position])

        holder.itemView.setOnClickListener {
            itemClickListener.onHabitItemClick(habits[position])
        }
    }

    override fun getItemCount(): Int {
        return habits.size
    }

    fun setHabits(habits: MutableList<Habit>) {
        this.habits = habits
    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        private val name: TextView = view.findViewById(R.id.habitName)
        private val description: TextView = view.findViewById(R.id.habitDescription)
        private val color: ImageView = view.findViewById(R.id.selectedHabitColor)
        private val type: TextView = view.findViewById(R.id.habitType)
        private val frequency: TextView = view.findViewById(R.id.habitFrequency)
        private val priority: TextView = view.findViewById(R.id.habitPriority)

        fun bind(habit: Habit) {
            name.text = habit.name
            description.text = habit.description
            color.setColorFilter(habit.color)
            type.text = habit.type.toString()
            priority.text = habit.priority.toString()

            val countTimesString = view.resources.getQuantityString(R.plurals.plurals_frequency_times, habit.frequencyTimes, habit.frequencyTimes)
            val frequencyString = habit.frequency.toString()

            frequency.text = "$countTimesString $frequencyString"
        }

    }

    interface OnHabitItemListener {
        fun onHabitItemClick(habit: Habit)
    }

}