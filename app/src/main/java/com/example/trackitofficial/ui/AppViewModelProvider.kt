package com.example.trackitofficial.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.trackitofficial.TrackItApplication
import com.example.trackitofficial.ui.home.HomeViewModel

/**
 * Provides Factory to create instance of ViewModel for the entire Inventory app
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Initializer for ItemEditViewModel
        initializer {
            WorkoutEditViewModel(
                this.createSavedStateHandle(),
                trackItApplication().container.workouts
            )
        }
        // Initializer for ItemEntryViewModel
        initializer {
            TrackItApplication(trackItApplication().container.workouts)
        }

        // Initializer for ItemDetailsViewModel
        initializer {
            WorkoutDetailsViewModel(
                this.createSavedStateHandle(),
                trackItApplication().container.workouts
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