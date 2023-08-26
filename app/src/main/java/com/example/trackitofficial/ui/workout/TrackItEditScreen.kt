import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.example.trackitofficial.ui.navigation.NavigationDestination
import com.example.trackitofficial.ui.theme.TrackItOfficialTheme
import com.example.trackitofficial.ui.workout.TrackItEditViewModel
import com.example.trackitofficial.ui.workout.WorkoutEntryBody
import kotlinx.coroutines.launch

object WorkoutEditDestination : NavigationDestination {
    override val route = "edit_workout"
    override val titleRes = R.string.edit_workout_title
    const val itemIdArg = "itemId"
    val routeWithArgs = "$route/{$itemIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrackItEditScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TrackItEditViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TrackItTopAppBar(
                title = stringResource(WorkoutEditDestination.titleRes),
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        },
        modifier = modifier
    ) { innerPadding ->
        WorkoutEntryBody(
            workoutUiState = viewModel.workoutUiState,
            onItemValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateWorkout()
                    navigateBack()
                }
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ItemEditScreenPreview() {
    TrackItOfficialTheme {
        TrackItEditScreen(navigateBack = { /*Do nothing*/ }, onNavigateUp = { /*Do nothing*/ })
    }
}