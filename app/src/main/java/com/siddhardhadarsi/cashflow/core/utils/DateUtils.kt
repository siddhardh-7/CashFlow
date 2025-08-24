package com.siddhardhadarsi.cashflow.core.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    fun getCurrentDate(): String {
        return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            .format(Date())
    }

    fun formatDate(timestamp: Long): String {
        return SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
            .format(Date(timestamp))
    }

    fun formatTime(timestamp: Long): String {
        return SimpleDateFormat("HH:mm", Locale.getDefault())
            .format(Date(timestamp))
    }

    fun getDateDaysAgo(days: Int): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_MONTH, -days)
        return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            .format(calendar.time)
    }

    fun getLastNDays(n: Int): List<String> {
        val dates = mutableListOf<String>()
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val calendar = Calendar.getInstance()

        repeat(n) {
            dates.add(formatter.format(calendar.time))
            calendar.add(Calendar.DAY_OF_MONTH, -1)
        }

        return dates.reversed()
    }
}