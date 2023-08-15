package com.example.trackitofficial.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.trackitofficial.TrackItApplication
import com.example.trackitofficial.ui.home.HomeViewModel
import com.example.trackitofficial.ui.workout.TrackItDetailsViewModel
import com.example.trackitofficial.ui.workout.TrackItEditViewModel
import com.example.trackitofficial.ui.workout.WorkoutEntryViewModel

/**
 * Provides Factory to create instance of ViewModel for the entire Inventory app
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Initializer for ItemEditViewModel
        initializer {
            TrackItEditViewModel(
                this.createSavedStateHandle(),
            )
        }
        // Initializer for ItemEntryViewModel
        initializer {
            WorkoutEntryViewModel()
        }

        // Initializer for ItemDetailsViewModel
        initializer {
            TrackItDetailsViewModel(
                this.createSavedStateHandle(),
            )
        }

        // Initializer for HomeViewModel
        initializer {
            HomeViewModel(trackItApplication().container.workouts)
        }
    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [WorkoutApplication].
 */
fun CreationExtras.trackItApplication(): TrackItApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as TrackItApplication)