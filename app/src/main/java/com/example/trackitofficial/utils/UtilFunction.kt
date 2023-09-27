package com.example.trackitofficial.utils

import androidx.compose.ui.graphics.Color
import androidx.room.TypeConverter
import com.example.trackitofficial.R
import com.example.trackitofficial.ui.theme.AngryColor
import com.example.trackitofficial.ui.theme.AwfulColor
import com.example.trackitofficial.ui.theme.BoredColor
import com.example.trackitofficial.ui.theme.CalmColor
import com.example.trackitofficial.ui.theme.DepressedColor
import com.example.trackitofficial.ui.theme.DisappointedColor
import com.example.trackitofficial.ui.theme.HappyColor
import com.example.trackitofficial.ui.theme.HumorousColor
import com.example.trackitofficial.ui.theme.LonelyColor
import com.example.trackitofficial.ui.theme.MysteriousColor
import com.example.trackitofficial.ui.theme.NeutralColor
import com.example.trackitofficial.ui.theme.ShamefulColor
import com.example.trackitofficial.ui.theme.SurprisedColor
import com.example.trackitofficial.ui.theme.SuspiciousColor
import com.example.trackitofficial.ui.theme.TenseColor
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

enum class Mood(
    val icon: Int,
    val contentColor: Color,
    val containerColor: Color
) {
    Neutral(
        icon = R.drawable.neutral,
        contentColor = Color.Black,
        containerColor = NeutralColor
    ),
    Happy(
        icon = R.drawable.happy,
        contentColor = Color.Black,
        containerColor = HappyColor
    ),
    Angry(
        icon = R.drawable.angry,
        contentColor = Color.White,
        containerColor = AngryColor
    ),
    Bored(
        icon = R.drawable.bored,
        contentColor = Color.Black,
        containerColor = BoredColor
    ),
    Calm(
        icon = R.drawable.calm,
        contentColor = Color.Black,
        containerColor = CalmColor
    ),
    Depressed(
        icon = R.drawable.depressed,
        contentColor = Color.Black,
        containerColor = DepressedColor
    ),
    Disappointed(
        icon = R.drawable.disappointed,
        contentColor = Color.White,
        containerColor = DisappointedColor
    ),
    Humorous(
        icon = R.drawable.humorous,
        contentColor = Color.Black,
        containerColor = HumorousColor
    ),
    Lonely(
        icon = R.drawable.lonely,
        contentColor = Color.White,
        containerColor = LonelyColor
    ),
    Mysterious(
        icon = R.drawable.mysterious,
        contentColor = Color.Black,
        containerColor = MysteriousColor
    ),
    Shameful(
        icon = R.drawable.shameful,
        contentColor = Color.White,
        containerColor = ShamefulColor
    ),
    Awful(
        icon = R.drawable.awful,
        contentColor = Color.Black,
        containerColor = AwfulColor
    ),
    Surprised(
        icon = R.drawable.surprised,
        contentColor = Color.Black,
        containerColor = SurprisedColor
    ),
    Suspicious(
        icon = R.drawable.suspicious,
        contentColor = Color.Black,
        containerColor = SuspiciousColor
    ),
    Tense(
        icon = R.drawable.tense,
        contentColor = Color.Black,
        containerColor = TenseColor
    )
}

