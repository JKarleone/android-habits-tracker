package com.example.habitstracker.utils

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.habitstracker.R
import com.example.habitstracker.utils.Util.Companion.dpToPx

class ColorPicker(private val fragment: Fragment,
                  private val parentView: LinearLayout) {

    private var scrollViewWidth: Int = 0
    private lateinit var colors: IntArray
    private lateinit var gradient: GradientDrawable
    private val colorViews = mutableListOf<ImageView>()

    init {
        createColorSquares()

        parentView.post {

            scrollViewWidth = parentView.right - parentView.left
            colors = IntArray(scrollViewWidth)

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
            parentView.background = gradient

            setColorsOfColorSquares()
        }

    }

    private fun createColorSquares() {

        for (i in 0 until 16) {
            val view = ImageView(fragment.requireContext())

            view.apply {
                setImageResource(R.drawable.ic_habit_color_square)
                background = ContextCompat.getDrawable(fragment.requireContext(), R.drawable.ic_habit_color_border)
                setPadding(1.dpToPx,1.dpToPx,1.dpToPx,1.dpToPx)

                setOnClickListener {
                    (fragment as? OnColorSquareItemListener)?.onColorSquareItemClick(it)
                }
                layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT).apply {
                    setMargins(20.dpToPx, 10.dpToPx, 20.dpToPx, 10.dpToPx)
                }
            }

            colorViews.add(view)

            parentView.addView(view)
        }

    }

    private fun setColorsOfColorSquares() {
        for (view in colorViews) {
            view.post {
                val color = getColorByPosition(view.left, view.right)
                view.setColorFilter(color)
                view.tag = color
            }
        }
    }

    private fun getColorByPosition(left: Int, right: Int): Int {
        val centerPosition = left - parentView.left + (right - left) / 2
        return colors[centerPosition]
    }

    interface OnColorSquareItemListener {
        fun onColorSquareItemClick(view: View)
    }

}