package com.example.trackitofficial.ui.workout

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.data.EmptyGroup.name
import androidx.lifecycle.ViewModel
import com.example.trackitofficial.data.db.Workout

/**
 * ViewModel to validate and insert items in the Room database.
 */
class WorkoutEntryViewModel : ViewModel() {

    /**
     * Holds current item ui state
     */
    var itemUiState by mutableStateOf(WorkoutUiState())
        private set

    /**
     * Updates the [itemUiState] with the value provided in the argument. This method also triggers
     * a validation for input values.
     */
    fun updateUiState(itemDetails: WorkoutDetails) {
        itemUiState =
            WorkoutUiState(itemDetails = itemDetails, isEntryValid = validateInput(itemDetails))
    }

    private fun validateInput(uiState: WorkoutDetails = itemUiState.itemDetails): Boolean {
        return with(uiState) {
            name.isNotBlank() && price.isNotBlank() && quantity.isNotBlank()
        }
    }
}

/**
 * Represents Ui State for an Item.
 */
data class WorkoutUiState(
    val itemDetails: WorkoutDetails = WorkoutDetails(),
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
fun Workout.toItemUiState(isEntryValid: Boolean = false): WorkoutUiState = ItemUiState(
    itemDetails = this.toItemDetails(),
    isEntryValid = isEntryValid
)

/**
 * Extension function to convert [Item] to [WorkoutDetails]
 */
fun Workout.toItemDetails(): WorkoutDetails = ItemDetails(
    id = id,
    name = name,
    price = price.toString(),
    quantity = quantity.toString()
)