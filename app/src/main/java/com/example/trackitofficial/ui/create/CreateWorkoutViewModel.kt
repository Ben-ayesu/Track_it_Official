package com.example.trackitofficial.ui.create

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.trackitofficial.data.db.entities.Workout
import com.example.trackitofficial.data.db.repo.WorkoutRepo
import com.example.trackitofficial.utils.Mood
import com.example.trackitofficial.utils.UtilFunctions.Companion.getFormattedDate
import com.example.trackitofficial.utils.UtilFunctions.Companion.getFormattedTime
import com.example.trackitofficial.utils.UtilFunctions.Companion.parseDate
import com.example.trackitofficial.utils.UtilFunctions.Companion.parseTime
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class CreateWorkoutViewModel(private val workoutRepo: WorkoutRepo) : ViewModel() {
    /**
     * Holds current item ui state
     */
    var workoutUiState by mutableStateOf(WorkoutUiState())


    // Handle the formatting of date in ui layer
    val sdf = SimpleDateFormat("MMMM d, yyyy", Locale.US)
    val formattedDate = sdf.format(Date())
    val sdfTime = SimpleDateFormat("HH:mm", Locale.US)
    val formattedTime = sdfTime.format(Date())

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
    val mood: String = Mood.Neutral.name,
    val rating: String = "",
    var date: String = "",
    var time: String = ""
)

/**
 * Extension function to convert [WorkoutDetails] to [Workout]
 */
fun WorkoutDetails.toWorkout(): Workout = Workout(
    workoutId = id,
    workoutTitle = title,
    workoutDescription = description,
    workoutMood = mood,
    workoutRating = rating,
    workoutDate = parseDate(date),
    workoutTime = parseTime(time)
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
fun Workout.toWorkoutDetails(): WorkoutDetails {
    val formattedDate = getFormattedDate(workoutDate)
    val formattedTime = getFormattedTime(workoutTime)

    return WorkoutDetails(
        id = workoutId,
        title = workoutTitle,
        description = workoutDescription,
        mood = workoutMood,
        rating = workoutRating,
        date = formattedDate,
        time = formattedTime
    )
}