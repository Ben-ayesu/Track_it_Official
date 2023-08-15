package com.example.trackitofficial.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.trackitofficial.data.WorkoutRepo
import com.example.trackitofficial.data.db.Workout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainViewModel(
    private val workoutRepo: WorkoutRepo
) {
    val workouts = workoutRepo.getWorkouts()

    private val viewModelScope = CoroutineScope(Dispatchers.IO)

    init {
        loadWorkouts()
    }

    private fun loadWorkouts(){
        viewModelScope.launch {
            val list = workoutRepo.getWorkouts().toMutableList()
//            list.sortByDescending { it.id }
        }
    }

    fun addWorkout(workout: Workout) = runBlocking { workoutRepo.addWorkout(workouts) }
}