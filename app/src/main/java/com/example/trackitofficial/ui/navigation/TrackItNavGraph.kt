package com.example.trackitofficial.ui.navigation

import CreateWorkoutEntryDestination
import CreateWorkoutScreen
import TrackItEditScreen
import WorkoutEditDestination
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.trackitofficial.ui.home.HomeDestination
import com.example.trackitofficial.ui.home.HomeScreen
import com.example.trackitofficial.ui.workout.WorkoutDetailsDestination
import com.example.trackitofficial.ui.workout.WorkoutDetailsScreen

/**
 * Provides Navigation graph for the application.
 */
@Composable
fun TrackItNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen(
                navigateToCreateWorkoutEntry = { navController.navigate(CreateWorkoutEntryDestination.route) },
                navigateToItemUpdate = {
                    navController.navigate("${WorkoutDetailsDestination.route}/${it}")
                }
            )
        }
        composable(route = CreateWorkoutEntryDestination.route) {
            CreateWorkoutScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() },
            )
        }
//        composable(route = WorkoutEntryDestination.route) {
//            WorkoutEntryScreen(
//                navigateBack = { navController.popBackStack() },
//                onNavigateUp = { navController.navigateUp() }
//            )
//        }
        composable(
            route = WorkoutDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(WorkoutDetailsDestination.itemIdArg) {
                type = NavType.IntType
            })
        ) {
            WorkoutDetailsScreen(
                navigateToEditItem = { navController.navigate("${WorkoutEditDestination.route}/$it") },
                navigateBack = { navController.navigateUp() }
            )
        }
        composable(
            route = WorkoutEditDestination.routeWithArgs,
            arguments = listOf(navArgument(WorkoutEditDestination.itemIdArg) {
                type = NavType.IntType
            })
        ) {
            TrackItEditScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}