package com.example.trackitofficial.ui.create

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.trackitofficial.data.db.entities.Workout
import com.example.trackitofficial.data.db.repo.WorkoutRepo
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class CreateWorkoutViewModel(
//    savedStateHandle: SavedStateHandle,
    private val workoutRepo: WorkoutRepo,
    existingWorkout: Workout? = null
) : ViewModel() {
    /**
     * Holds current item ui state
     */
    // Initialize ViewModel state based on whether an existing workout is provided
    var workoutUiState by mutableStateOf(
        existingWorkout?.toWorkoutUiState(true) ?: WorkoutUiState()
    )


    // Handle the formatting of date in ui layer
    val sdf = SimpleDateFormat("MMMM d, yyyy", Locale.US)
    val formattedDate = sdf.format(Date())
    val sdfTime = SimpleDateFormat("HH:mm", Locale.US)
    val formattedTime = sdfTime.format(Date())

//    private val itemId: Int = checkNotNull(savedStateHandle[WorkoutDetailsDestination.itemIdArg])
//    private val itemId: Int = savedStateHandle.get<Int>(WorkoutDetailsDestination.itemIdArg) ?: 0

    /**
     * Holds the item details ui state. The data is retrieved from [workoutRepo] and mapped to
     * the UI state.
     */
//    val uiState: StateFlow<WorkoutDetailsUiState> =
//        workoutRepo.getWorkout(itemId)
//            .filterNotNull()
//            .map {
//                WorkoutDetailsUiState(workoutDetails = it.toWorkoutDetails())
//            }.stateIn(
//                scope = viewModelScope,
//                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
//                initialValue = WorkoutDetailsUiState()
//            )

//    init {
//        viewModelScope.launch {
//            workoutUiState = workoutRepo.getWorkout(itemId)
//                .filterNotNull()
//                .first()
//                .toWorkoutUiState(true)
//        }
//    }

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
            if (workoutUiState.workoutDetails.toWorkout().workoutId == 0) {
                workoutRepo.insertWorkout(workoutUiState.workoutDetails.toWorkout())
            } else {
                workoutRepo.updateWorkout(workoutUiState.workoutDetails.toWorkout())
            }
        }
    }

    /**
     * Update the workout in the [WorkoutRepo]'s data source
     */
//    suspend fun updateWorkout() {
//        if (validateInput(workoutUiState.workoutDetails)) {
//            workoutRepo.updateWorkout(workoutUiState.workoutDetails.toWorkout())
//        }
//    }

    /**
     * Deletes the item from the [workoutRepo]'s data source.
     */
//    suspend fun deleteItem() {
//        workoutRepo.deleteWorkout(uiState.value.workoutDetails.toWorkout())
//    }

    private fun validateInput(uiState: WorkoutDetails = workoutUiState.workoutDetails): Boolean {
        return with(uiState) {
            title.isNotBlank() && description.isNotBlank() && rating.isNotBlank()
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
    var date: String = "",
    var time: String = ""
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
    workoutDate = date,
    workoutTime = time
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
    date = workoutDate,
    time = workoutTime
)