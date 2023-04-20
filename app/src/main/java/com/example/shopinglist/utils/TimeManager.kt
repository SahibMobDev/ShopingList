package com.example.shopinglist.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object TimeManager {

    fun getCurrentTime(): String {
        val formatter = SimpleDateFormat("hh:mm:ss - yyyy/MM/dd", Locale.getDefault())
        return formatter.format(Calendar.getInstance().time)
    }
}