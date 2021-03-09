package com.example.habitstracker.utils

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.Log
import com.example.habitstracker.utils.Util.Companion.dpToPx

class ColorPicker(private val left: Int, private val right: Int) {

    private val scrollViewWidth = right - left
    private val colors = IntArray(scrollViewWidth)
    var gradient: GradientDrawable

    init {
        for (index in 0 until scrollViewWidth) {
            val hsv = FloatArray(3).apply {
                set(0, 359 * index.toFloat() / scrollViewWidth)
                set(1, 1.0f)
                set(2, 1.0f)
            }
            colors[index] = Color.HSVToColor(hsv)
        }

        gradient = GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, colors)
        gradient.cornerRadius = 10.dpToPx.toFloat()
    }

    fun getColorByPosition(left: Int, right: Int): Int {
        val centerPosition = left - this.left + (right - left) / 2
        Log.d("HabitActivity", centerPosition.toString())
        return colors[centerPosition]
    }

}