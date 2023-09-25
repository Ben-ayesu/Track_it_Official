package com.example.trackitofficial.ui.navigation

import CreateWorkoutEntryDestination
import CreateWorkoutScreen
import TrackItEditScreen
import WorkoutEditDestination
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.trackitofficial.ui.home.HomeDestination
import com.example.trackitofficial.ui.home.HomeScreen

/**
 * Provides Navigation graph for the application.
 */
@Composable
fun TrackItNavHost(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen(
                navigateToCreateWorkoutEntry = {
                    navController.navigate(
                        CreateWorkoutEntryDestination.route
                    )
                },
                navigateToItemUpdate = {
                    navController.navigate("${WorkoutEditDestination.route}/$it")
                }
            )
        }
        composable(route = CreateWorkoutEntryDestination.route) {
            CreateWorkoutScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() },
            )
        }
        composable(
            route = WorkoutEditDestination.routeWithArgs,
            arguments = listOf( navArgument(WorkoutEditDestination.itemIdArg) { type = NavType.IntType } )
        ) {
            TrackItEditScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}