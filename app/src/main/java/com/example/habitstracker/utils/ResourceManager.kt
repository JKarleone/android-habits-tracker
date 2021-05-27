package com.example.habitstracker.utils

import android.content.Context
import javax.inject.Inject

class ResourceManager @Inject constructor(private val context: Context) {

    fun getResourceString(resourceId: Int): String {
        return context.getString(resourceId)
    }

}