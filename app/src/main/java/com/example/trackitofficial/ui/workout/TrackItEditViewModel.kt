package com.example.trackitofficial.ui.workout

import WorkoutEditDestination
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

/**
 * ViewModel to retrieve and update an item from the [ItemsRepository]'s data source.
 */
class TrackItEditViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    /**
     * Holds current item ui state
     */
    var workoutUiState by mutableStateOf(WorkoutUiState())
        private set

    private val itemId: Int = checkNotNull(savedStateHandle[WorkoutEditDestination.itemIdArg])

    private fun validateInput(uiState: WorkoutDetails = workoutUiState.workoutDetails): Boolean {
        return with(uiState) {
            name.isNotBlank() && price.isNotBlank() && quantity.isNotBlank()
        }
    }
}