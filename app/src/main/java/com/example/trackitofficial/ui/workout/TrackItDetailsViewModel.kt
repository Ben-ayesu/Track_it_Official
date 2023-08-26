package com.example.trackitofficial.ui.workout

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trackitofficial.data.db.repo.WorkoutRepo
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

/**
 * ViewModel to retrieve, update and delete an item from the [workoutRepo]'s data source.
 */
class TrackItDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val workoutRepo: WorkoutRepo
) : ViewModel() {

    private val itemId: Int = checkNotNull(savedStateHandle[WorkoutDetailsDestination.itemIdArg])

    /**
     * Holds the item details ui state. The data is retrieved from [workoutRepo] and mapped to
     * the UI state.
     */
    val uiState: StateFlow<WorkoutDetailsUiState> =
        workoutRepo.getWorkout(itemId)
            .filterNotNull()
            .map {
                WorkoutDetailsUiState(workoutDetails = it.toWorkoutDetails())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = WorkoutDetailsUiState()
            )

    /**
     * Deletes the item from the [workoutRepo]'s data source.
     */
    suspend fun deleteItem() {
        workoutRepo.deleteWorkout(uiState.value.workoutDetails.toWorkout())
    }
                companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

/**
 * UI state for WorkoutDetailsScreen
 */
data class WorkoutDetailsUiState(
    val workoutDetails: WorkoutDetails = WorkoutDetails()
)