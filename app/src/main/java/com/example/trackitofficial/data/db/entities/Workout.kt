package com.example.trackitofficial.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.trackitofficial.utils.DateObjectConverter
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
    val workoutRating: String,
    @TypeConverters(DateObjectConverter::class)
    val workoutDate: Date,
    @TypeConverters(DateObjectConverter::class)
    val workoutTime: Date
)