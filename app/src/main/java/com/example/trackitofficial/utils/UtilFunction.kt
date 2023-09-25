package com.example.trackitofficial.utils

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class UtilFunctions {
    companion object {
        private val dateFormat = SimpleDateFormat("MMMM d, yyyy", Locale.US)
        private val timeFormat = SimpleDateFormat("HH:mm", Locale.US)

        fun getFormattedDate(date: Date): String {
            return dateFormat.format(date)
        }

        fun getFormattedTime(time: Date): String {
            return timeFormat.format(time)
        }

        fun parseDate(dateString: String): Date {
            return try {
                dateFormat.parse(dateString) ?: Date()
            } catch (e: Exception) {
                Date() // Set to the current date as a default
            }
        }

        fun parseTime(timeString: String): Date {
            return try {
                timeFormat.parse(timeString) ?: Date()
            } catch (e: Exception) {
                Date() // Set to the current time as a default
            }
        }
    }
}

class DateObjectConverter {
    @TypeConverter
    fun timestampToDate(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}

