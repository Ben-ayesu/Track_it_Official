package com.example.trackitofficial.ui.create

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.trackitofficial.data.db.repo.WorkoutRepo

class CreateWorkoutViewModel(
    savedStateHandle: SavedStateHandle,
    private val workoutRepo: WorkoutRepo
) : ViewModel() {

}