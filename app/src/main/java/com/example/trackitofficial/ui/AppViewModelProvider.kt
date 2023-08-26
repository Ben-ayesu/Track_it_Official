package com.example.trackitofficial.ui

import android.app.Application
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.trackitofficial.TrackItApplication
import com.example.trackitofficial.ui.home.HomeViewModel
import com.example.trackitofficial.ui.workout.TrackItDetailsViewModel
import com.example.trackitofficial.ui.workout.TrackItEditViewModel
import com.example.trackitofficial.ui.workout.TrackEntryItViewModel

/**
 * Provides Factory to create instance of ViewModel for the entire Inventory app
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Initializer for WorkoutEditViewModel
        initializer {
            TrackItEditViewModel(
                this.createSavedStateHandle(),
                trackItApplication().container.workoutsRepository
            )
        }
        // Initializer for WorkoutEntryViewModel
        initializer {
            TrackEntryItViewModel(trackItApplication().container.workoutsRepository)
        }

        // Initializer for WorkoutDetailsViewModel
        initializer {
            TrackItDetailsViewModel(
                this.createSavedStateHandle(),
                trackItApplication().container.workoutsRepository
            )
        }

        // Initializer for HomeViewModel
        initializer {
            HomeViewModel(trackItApplication().container.workoutsRepository)
        }
    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [TrackItApplication].
 */
fun CreationExtras.trackItApplication(): TrackItApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as TrackItApplication)