package com.example.trackitofficial.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


/**
 * Database access object to access the Inventory database
 */
@Dao
interface WorkoutDao {
    // Specify the conflict strategy as IGNORE, when the user tries to add an
    // existing Item into the database Room ignores the conflict.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addWorkout(workout: Workout)

    @Update
    suspend fun updateWorkout(workout: Workout)

    @Delete
    suspend fun deleteWorkout(workout: Workout)

    @Query("SELECT * from workouts WHERE workout_id = :id")
    fun getItem(id: Int): Flow<Workout>

    @Query("SELECT * FROM workouts ORDER BY workout_id DESC")
    fun getAllWorkouts(): Flow<List<Workout>>
}