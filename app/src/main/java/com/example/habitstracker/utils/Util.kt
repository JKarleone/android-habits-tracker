package com.example.habitstracker.utils

import android.content.res.Resources
import kotlin.math.roundToInt

class Util {

    companion object {
        val Int.dpToPx: Int
            get() = (this * Resources.getSystem().displayMetrics.density).roundToInt()
    }

}