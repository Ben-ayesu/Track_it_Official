package com.example.trackitofficial.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface WorkoutDao {
    @Query("SELECT * FROM workouts")
    suspend fun getAll(): List<Workout>

    @Insert
    suspend fun addWorkout(workout: Workout): Long

    @Update
    suspend fun update(workout: Workout)

    @Delete
    suspend fun delete(workout: Workout)
}