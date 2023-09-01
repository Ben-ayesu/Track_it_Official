import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateWorkoutScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    canNavigateBack: Boolean = true,
    viewModel: CreateWorkoutViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val couroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TrackItTopAppBar(
                title = stringResource(id = CreateWorkoutEntryDestination.titleRes),
                canNavigateBack = canNavigateBack,
                navigateUp = onNavigateUp,
                scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            items(100) {
                Text(text = "Items $it")
            }
        }
//        CreateWorkoutBody(
//            modifier = modifier
//                .padding(innerPadding)
//                .fillMaxSize()
//        )
    }
}

@Composable
private fun CreateWorkoutBody(
    modifier: Modifier = Modifier
) {

}

@Preview
@Composable
fun CreateWorkoutScreenPreview() {
    TrackItOfficialTheme {
        CreateWorkoutBody()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun CreateWorkoutTopAppBarPreviewCanNaVBa() {
    TrackItOfficialTheme {
        Column {
            TrackItTopAppBar(title = "Create Workout", canNavigateBack = false, navigateUp = {})
            TrackItTopAppBar(title = "Create Workout", canNavigateBack = true, navigateUp = {})
        }
    }
}


