import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.trackitofficial.R
import com.example.trackitofficial.TrackItTopAppBar
import com.example.trackitofficial.ui.AppViewModelProvider
import com.example.trackitofficial.ui.create.CreateWorkoutViewModel
import com.example.trackitofficial.ui.navigation.NavigationDestination
import com.example.trackitofficial.ui.theme.TrackItOfficialTheme
import com.example.trackitofficial.ui.workout.WorkoutDetails
import com.example.trackitofficial.ui.workout.WorkoutEntryBody
import com.example.trackitofficial.ui.workout.WorkoutUiState

object CreateWorkoutEntryDestination : NavigationDestination {
    override val route = "create_workout_entry"
    override val titleRes = R.string.date
}

@Composable
fun CreateWorkoutScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    canNavigateBack: Boolean = true,
    modifier: Modifier = Modifier,
    viewModel: CreateWorkoutViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val couroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TrackItTopAppBar(
                title = stringResource(id = CreateWorkoutEntryDestination.titleRes),
                canNavigateBack = canNavigateBack,
                navigateUp = onNavigateUp
            )
        }
    ) { innerPadding ->
        
    }

    @Preview
    @Composable
    fun CreateWorkoutScreenPreview() {
        TrackItOfficialTheme {
            WorkoutEntryBody(workoutUiState = WorkoutUiState(
                WorkoutDetails(
                    title = "Item name", description = "10.00", date = "5"
                )
            ), onItemValueChange = {}, onSaveClick = {})
        }
    }

}