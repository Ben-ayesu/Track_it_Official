package com.example.trackitofficial.ui.workout

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

/**
 * ViewModel to retrieve, update and delete an item from the [ItemsRepository]'s data source.
 */
class TrackItDetailsViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val itemId: Int = checkNotNull(savedStateHandle[WorkoutDetailsDestination.itemIdArg])

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

/**
 * UI state for WorkoutDetailsScreen
 */
data class WorkoutDetailsUiState(
    val itemDetails: WorkoutDetails = WorkoutDetails()
)