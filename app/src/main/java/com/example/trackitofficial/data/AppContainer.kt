package com.example.trackitofficial.data

import android.content.Context
import com.example.trackitofficial.data.db.WorkoutDatabase
import com.example.trackitofficial.data.db.repo.OfflineWorkoutRepo
import com.example.trackitofficial.data.db.repo.WorkoutRepo

/**
 * App container for Dependency injection.
 */
interface AppContainer {
    val workoutsRepository: WorkoutRepo
}

/**
 * [AppContainer] implementation that provides instance of [OfflineWorkoutRepo]
 */
class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [WorkoutRepo]
     */
    override val workoutsRepository: WorkoutRepo by lazy {
        OfflineWorkoutRepo(WorkoutDatabase.getDatabase(context).workoutDao())
    }
}