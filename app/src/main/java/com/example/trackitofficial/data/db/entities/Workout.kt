package com.example.trackitofficial.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity data class represents a single row in the database.
 */
@Entity(tableName = "workouts")
data class Workout(
    @PrimaryKey(autoGenerate = true)
    val workoutId: Int = 0,
    val workoutDate: String,
    val workoutTitle: String,
    val workoutDescription: String,
    val workoutRating: String,
)