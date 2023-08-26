package com.example.trackitofficial.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity data class represents a single row in the database.
 */
@Entity(tableName = "workouts")
data class Workout(
    @PrimaryKey(autoGenerate = true)
    val workoutId: Int = 0,
    val workoutTitle: String,
    val workoutDescription: String,
    val workoutLastModified: String,
    val workoutRating: String,
)