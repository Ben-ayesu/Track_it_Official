package com.example.trackitofficial.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.trackitofficial.utils.DateObjectConverter
import com.example.trackitofficial.utils.Mood
import java.util.Date

/**
 * Entity data class represents a single row in the database.
 */
@Entity(tableName = "workouts")
data class Workout(
    @PrimaryKey(autoGenerate = true)
    val workoutId: Int = 0,
    val workoutTitle: String,
    val workoutDescription: String,
    val workoutMood: String = Mood.Neutral.name,
    val workoutRating: String,
    @TypeConverters(DateObjectConverter::class)
    val workoutDate: Date,
    @TypeConverters(DateObjectConverter::class)
    val workoutTime: Date
)