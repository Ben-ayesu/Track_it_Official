package com.example.trackitofficial.ui.home

import androidx.lifecycle.ViewModel
import com.example.trackitofficial.data.WorkoutRepo
import com.example.trackitofficial.data.db.Workout

/**
 * ViewModel to retrieve all items in the Room database.
 */
class HomeViewModel() : ViewModel() {

    /**
     * Holds home ui state. The list of items are retrieved from [WorkoutRepo] and mapped to
     * [HomeUiState]
     */
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

/**
 * Ui State for HomeScreen
 */
data class HomeUiState(val workoutList: List<Workout> = listOf())