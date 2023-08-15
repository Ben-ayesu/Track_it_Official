package com.example.trackitofficial.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity data class represents a single row in the database.
 */
@Entity(tableName = "workouts")
data class Workout(
    @ColumnInfo(name = "workout_title") val title: String,
    @ColumnInfo(name = "workout_description") val description: String,
    @ColumnInfo(name = "workout_lastModified") val lastModified: String,
    @ColumnInfo(name = "workout_rating") val rating: String,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "workout_id") val id: Int = 0,
)