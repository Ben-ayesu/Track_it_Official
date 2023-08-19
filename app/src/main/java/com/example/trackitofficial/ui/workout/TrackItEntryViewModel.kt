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
class WorkoutEntryViewModel(private val workoutRepo: WorkoutRepo) : ViewModel() {

    /**
     * Holds current item ui state
     */
    var workoutUiState by mutableStateOf(WorkoutUiState())
        private set

    /**
     * Updates the [workoutUiState] with the value provided in the argument. This method also triggers
     * a validation for input values.
     */
    fun updateUiState(itemDetails: WorkoutDetails) {
        workoutUiState =
            WorkoutUiState(workoutDetails = itemDetails, isEntryValid = validateInput(itemDetails))
    }

    private fun validateInput(uiState: WorkoutDetails = workoutUiState.workoutDetails): Boolean {
        return with(uiState) {
            name.isNotBlank() && price.isNotBlank() && quantity.isNotBlank()
        }
    }

    suspend fun saveWorkout() {
        if (validateInput()) {
            workoutRepo.insertWorkout(workoutUiState.workoutDetails.toItem())
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
    val name: String = "",
    val price: String = "",
    val rating: String = "",
    val quantity: String = "",
)

/**
 * Extension function to convert [WorkoutDetails] to [Item]. If the value of [WorkoutDetails.price] is
 * not a valid [Double], then the price will be set to 0.0. Similarly if the value of
 * [WorkoutDetails.quantity] is not a valid [Int], then the quantity will be set to 0
 */
fun WorkoutDetails.toItem(): Workout = Workout(
    id = id,
    title = name,
    description = price,
    rating = rating,
    lastModified = quantity
)

/**
 * Extension function to convert [Item] to [WorkoutUiState]
 */
fun Workout.toItemUiState(isEntryValid: Boolean = false): WorkoutUiState = WorkoutUiState(
    workoutDetails = this.towrkoutDetails(),
    isEntryValid = isEntryValid
)

/**
 * Extension function to convert [Item] to [WorkoutDetails]
 */
fun Workout.towrkoutDetails(): WorkoutDetails = WorkoutDetails(
    id = id,
    name = "",
    price = "",
    rating = "",
    quantity = ""
)