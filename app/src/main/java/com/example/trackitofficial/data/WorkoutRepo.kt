package com.example.trackitofficial.data

import com.example.trackitofficial.data.db.Workout
import com.example.trackitofficial.data.db.WorkoutDao

class WorkoutRepo(
    private val workoutDao: WorkoutDao
) {
    fun getWorkouts() = workoutDao.getAllWorkouts()
    suspend fun addWorkout(workout: Workout) = workoutDao.addWorkout(workout)
    suspend fun updateWorkout(workout: Workout) = workoutDao.addWorkout(workout)

    suspend fun deleteWorkout(workout: Workout) = workoutDao.addWorkout(workout)

}