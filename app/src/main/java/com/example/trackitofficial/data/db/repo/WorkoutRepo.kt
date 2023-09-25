package com.example.trackitofficial.data.db.repo

import com.example.trackitofficial.data.db.entities.Workout
import kotlinx.coroutines.flow.Flow

/**
 * Repository that provides insert, update, delete, and retrieve of [Workout] from a given data source.
 */
interface WorkoutRepo {
    /**
     * Retrieve all the workouts from the the given data source.
     */
    fun getAllWorkouts(): Flow<List<Workout>>

    /**
     * Retrieve an workout from the given data source that matches with the [id].
     */
    fun getWorkout(id: Int): Flow<Workout?>

    /**
     * Insert workout in the data source
     */
    suspend fun insertWorkout(workout: Workout)

    /**
     * Delete workout from the data source
     */
    suspend fun deleteWorkout(workout: Workout)

    /**
     * Update workout in the data source
     */
    suspend fun updateWorkout(workout: Workout)
}