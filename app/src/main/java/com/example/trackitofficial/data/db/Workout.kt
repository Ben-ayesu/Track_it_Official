package com.example.trackitofficial.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity data class represents a single row in the database.
 */
@Entity(tableName = "workouts")
data class Workout(
    @ColumnInfo(name = "workout_title") val workoutTitle: String,
    @ColumnInfo(name = "workout_description") val workoutDescription: String,
    @ColumnInfo(name = "workout_lastModified") val workoutLastModified: String,
    @ColumnInfo(name = "workout_rating") val workoutRating: String,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "workout_id") val workoutId: Int = 0,
)