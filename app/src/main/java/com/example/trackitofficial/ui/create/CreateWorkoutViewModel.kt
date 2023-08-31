package com.example.trackitofficial.ui.create

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trackitofficial.data.db.entities.Workout
import com.example.trackitofficial.data.db.repo.WorkoutRepo
import com.example.trackitofficial.ui.workout.WorkoutDetails
import com.example.trackitofficial.ui.workout.WorkoutDetailsDestination
import com.example.trackitofficial.ui.workout.WorkoutDetailsUiState
import com.example.trackitofficial.ui.workout.WorkoutUiState
import com.example.trackitofficial.ui.workout.toWorkout
import com.example.trackitofficial.ui.workout.toWorkoutDetails
import com.example.trackitofficial.ui.workout.toWorkoutUiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CreateWorkoutViewModel(
    savedStateHandle: SavedStateHandle,
    private val workoutRepo: WorkoutRepo
) : ViewModel() {
    /**
     * Holds current item ui state
     */
    var workoutUiState by mutableStateOf(WorkoutUiState())
        private set

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

    init {
        viewModelScope.launch {
            workoutUiState = workoutRepo.getWorkout(itemId)
                .filterNotNull()
                .first()
                .toWorkoutUiState(true)
        }
    }

    /**
     * Updates the [workoutUiState] with the value provided in the argument. This method also triggers
     * a validation for input values.
     */
    fun updateUiState(workoutDetails: WorkoutDetails) {
        workoutUiState =
            WorkoutUiState(
                workoutDetails = workoutDetails,
                isEntryValid = validateInput(workoutDetails)
            )
    }

    suspend fun saveWorkout() {
        if (validateInput()) {
            workoutRepo.insertWorkout(workoutUiState.workoutDetails.toWorkout())
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
     * Deletes the item from the [workoutRepo]'s data source.
     */
    suspend fun deleteItem() {
        workoutRepo.deleteWorkout(uiState.value.workoutDetails.toWorkout())
    }

    private fun validateInput(uiState: WorkoutDetails = workoutUiState.workoutDetails): Boolean {
        return with(uiState) {
            title.isNotBlank() && description.isNotBlank() && date.isNotBlank() && rating.isNotBlank()
        }
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

/**
 * Represents Ui State for a Workout.
 */
data class WorkoutUiState(
    val workoutDetails: WorkoutDetails = WorkoutDetails(),
    val isEntryValid: Boolean = false
)

data class WorkoutDetails(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val rating: String = "",
    val date: String = "",
)

/**
 * UI state for WorkoutDetailsScreen
 */
data class WorkoutDetailsUiState(
    val workoutDetails: WorkoutDetails = WorkoutDetails()
)

/**
 * Extension function to convert [WorkoutDetails] to [Workout]. If the value of [WorkoutDetails.description] is
 * not a valid [Double], then the price will be set to 0.0. Similarly if the value of
 * [WorkoutDetails.date] is not a valid [Int], then the quantity will be set to 0
 */
fun WorkoutDetails.toWorkout(): Workout = Workout(
    workoutId = id,
    workoutTitle = title,
    workoutDescription = description,
    workoutRating = rating,
    workoutDate = date
)

/**
 * Extension function to convert [Workout] to [WorkoutUiState]
 */
fun Workout.toWorkoutUiState(isEntryValid: Boolean = false): WorkoutUiState = WorkoutUiState(
    workoutDetails = this.toWorkoutDetails(),
    isEntryValid = isEntryValid
)


/**
 * Extension function to convert [Workout] to [WorkoutDetails]
 */
fun Workout.toWorkoutDetails(): WorkoutDetails = WorkoutDetails(
    id = workoutId,
    title = workoutTitle,
    description = workoutDescription,
    rating = workoutRating,
    date = workoutDate
)