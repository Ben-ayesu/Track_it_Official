package com.example.trackitofficial

import com.example.trackitofficial.data.db.repo.WorkoutRepo
import com.example.trackitofficial.ui.create.CreateWorkoutViewModel
import com.example.trackitofficial.ui.create.WorkoutDetails
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CreateWorkoutViewModelTest {

    @Mock
    private lateinit var workoutRepo: WorkoutRepo
    private lateinit var createWorkoutViewModel: CreateWorkoutViewModel

    @Test
    fun `test updateUiState sets isEntryValid to true when input is valid`() {
        MockitoAnnotations.initMocks(this)
        val workoutDetails = WorkoutDetails(
            title = "Bench Press",
            description = "A chest workout",
            rating = "5"
        )
        createWorkoutViewModel = CreateWorkoutViewModel(workoutRepo)
        createWorkoutViewModel.updateUiState(workoutDetails)
        assertTrue(createWorkoutViewModel.workoutUiState.isEntryValid)
    }

    @Test
    fun `test updateUiState sets isEntryValid to false when input is invalid`() {
        MockitoAnnotations.initMocks(this)
        val workoutDetails = WorkoutDetails(
            title = "",
            description = "",
            rating = ""
        )
        createWorkoutViewModel = CreateWorkoutViewModel(workoutRepo)
        createWorkoutViewModel.updateUiState(workoutDetails)
        assertFalse(createWorkoutViewModel.workoutUiState.isEntryValid)
    }
}