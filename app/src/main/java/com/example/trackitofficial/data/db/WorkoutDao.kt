package com.example.trackitofficial.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import java.util.concurrent.Flow

@Dao
interface WorkoutDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addWorkout(workout: Workout)

    @Update
    suspend fun update(workout: Workout)

    @Delete
    suspend fun delete(workout: Workout)

//    @Query("SELECT * from workouts WHERE workout_id = :id")
////    fun getItem(id: Int): Flow<Workout>
//
//    @Query("SELECT * FROM workouts ORDER BY workout_id DESC")
//    fun getAllWorkouts(): Flow<List<Workout>>
}