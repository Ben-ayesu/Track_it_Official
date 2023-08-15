package com.example.trackitofficial.data

import android.provider.ContactsContract
import com.example.trackitofficial.data.db.AppDatabase
import com.example.trackitofficial.data.db.Workout
import com.example.trackitofficial.data.db.WorkoutDao

class WorkoutRepo(
    private val workoutDao: WorkoutDao
) {
    suspend fun getWorkouts() = workoutDao.getAll()
    suspend fun addWorkout(workout: Workout) = workoutDao.addWorkout(workout)
    suspend fun updateWorkout(workout: Workout) = workoutDao.addWorkout(workout)

    suspend fun deleteWorkout(workout: Workout) = workoutDao.addWorkout(workout)

}