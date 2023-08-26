package com.example.trackitofficial.ui.workout

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.trackitofficial.data.WorkoutRepo
import com.example.trackitofficial.data.db.Workout

/**
 * ViewModel to validate and insert items in the Room database.
 */
class TrackEntryItViewModel(private val workoutRepo: WorkoutRepo) : ViewModel() {

    /**
     * Holds current item ui state
     */
    var workoutUiState by mutableStateOf(WorkoutUiState())
        private set

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

    suspend fun saveWorkout() {
        if (validateInput()) {
            workoutRepo.insertWorkout(workoutUiState.workoutDetails.toWorkout())
        }
    }
}

/**
 * Represents Ui State for an Item.
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
 * Extension function to convert [WorkoutDetails] to [Workout]. If the value of [WorkoutDetails.description] is
 * not a valid [Double], then the price will be set to 0.0. Similarly if the value of
 * [WorkoutDetails.date] is not a valid [Int], then the quantity will be set to 0
 */
fun WorkoutDetails.toWorkout(): Workout = Workout(
    workoutId = id,
    workoutTitle = title,
    workoutDescription = description,
    workoutRating = rating,
    workoutLastModified = date
)

/**
 * Extension function to convert [Workout] to [WorkoutUiState]
 */
fun Workout.toWorkoutUiState (isEntryValid: Boolean = false): WorkoutUiState = WorkoutUiState(
    workoutDetails = this.toWorkoutDetails(),
    isEntryValid = isEntryValid
)


/**
 * Extension function to convert [Workout] to [WorkoutDetails]
 */
fun Workout.toWorkoutDetails(): WorkoutDetails = WorkoutDetails(
    id = workoutId,
    title = "",
    description = "",
    rating = "",
    date = ""
)