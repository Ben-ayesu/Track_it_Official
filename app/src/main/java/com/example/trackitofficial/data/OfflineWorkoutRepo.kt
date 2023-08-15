package com.example.trackitofficial.data

import com.example.trackitofficial.data.db.Workout
import com.example.trackitofficial.data.db.WorkoutDao
import kotlinx.coroutines.flow.Flow

class OfflineWorkoutRepo(private val workoutDao: WorkoutDao) : WorkoutRepo {
    override fun getAllWorkouts(): Flow<List<Workout>> = workoutDao.getAllWorkouts()

    override fun getWorkout(id: Int): Flow<Workout?> = workoutDao.getItem(id)

    override suspend fun insertWorkout(workout: Workout) = workoutDao.addWorkout(workout)

    override suspend fun deleteWorkout(workout: Workout) = workoutDao.deleteWorkout(workout)

    override suspend fun updateWorkout(workout: Workout) = workoutDao.updateWorkout(workout)
}