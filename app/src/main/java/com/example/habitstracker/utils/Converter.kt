package com.example.habitstracker.utils

import androidx.databinding.InverseMethod

class Converter private constructor() {

    @InverseMethod("fromStringToInt")
    fun fromIntToString(number: Int?): String =
        number?.toString() ?: ""

    fun fromStringToInt(string: String): Int? =
        try {
            string.toInt()
        } catch (e: NumberFormatException) {
            null
        }

    companion object {

        private var instance: Converter? = null

        @JvmStatic
        fun getInstance(): Converter {
            if (instance == null)
                instance = Converter()

            return instance!!
        }

    }

}