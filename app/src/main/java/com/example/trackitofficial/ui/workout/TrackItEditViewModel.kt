package com.example.trackitofficial.ui.workout

import WorkoutEditDestination
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trackitofficial.data.WorkoutRepo
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

/**
 * ViewModel to retrieve and update an item from the [WorkoutRepo]'s data source.
 */
class TrackItEditViewModel(
    savedStateHandle: SavedStateHandle,
    private val workoutRepo: WorkoutRepo
) : ViewModel() {

    /**
     * Holds current item ui state
     */
    var workoutUiState by mutableStateOf(WorkoutUiState())
        private set

    private val itemId: Int = checkNotNull(savedStateHandle[WorkoutEditDestination.itemIdArg])

    init {
        viewModelScope.launch {
            workoutUiState = workoutRepo.getWorkout(itemId)
                .filterNotNull()
                .first()
                .toWorkoutUiState(true)
        }
    }

    /**
     * Update the workout in the [WorkoutRepo]'s data source
     */
    suspend fun updateWorkout() {
        if (validateInput(workoutUiState.workoutDetails)) {
            workoutRepo.updateWorkout(workoutUiState.workoutDetails.toWorkout())
        }
    }

    /**
     * Updates the [workoutUiState] with the value provided in the argument. This method also triggers
     * a validation for input values.
     */
    fun updateUiState(workoutDetails: WorkoutDetails) {
        workoutUiState =
            WorkoutUiState(workoutDetails = workoutDetails, isEntryValid = validateInput(workoutDetails))
    }

    private fun validateInput(uiState: WorkoutDetails = workoutUiState.workoutDetails): Boolean {
        return with(uiState) {
            title.isNotBlank() && description.isNotBlank() && date.isNotBlank()
        }
    }
}