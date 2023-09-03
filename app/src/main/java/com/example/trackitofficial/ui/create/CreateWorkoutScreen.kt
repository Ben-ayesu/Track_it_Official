import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.trackitofficial.R
import com.example.trackitofficial.TrackItTopAppBar
import com.example.trackitofficial.ui.AppViewModelProvider
import com.example.trackitofficial.ui.create.CreateWorkoutViewModel
import com.example.trackitofficial.ui.create.WorkoutDetails
import com.example.trackitofficial.ui.create.WorkoutUiState
import com.example.trackitofficial.ui.navigation.NavigationDestination
import com.example.trackitofficial.ui.theme.TrackItOfficialTheme
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object CreateWorkoutEntryDestination : NavigationDestination {
    override val route = "create_workout_entry"
    override val titleRes = R.string.new_workout
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateWorkoutScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    canNavigateBack: Boolean = true,
    viewModel: CreateWorkoutViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val sdf = SimpleDateFormat("MMMM d, yyyy", Locale.US)
    val formattedDate = sdf.format(Date())

    Scaffold(
        topBar = {
            TrackItTopAppBar(
                title = formattedDate,
                canNavigateBack = canNavigateBack,
                navigateUp = onNavigateUp,
            )
        }
    ) { innerPadding ->
        CreateWorkoutBody(
            workoutUiState = viewModel.workoutUiState,
            onItemValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveWorkout()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        )
    }
}

@Composable
fun CreateWorkoutBody(
    workoutUiState: WorkoutUiState,
    onItemValueChange: (WorkoutDetails) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium)),
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_small))
    ) {
        CreateWorkoutInputForm(
            workoutDetails = workoutUiState.workoutDetails,
            onValueChange = onItemValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            enabled = workoutUiState.isEntryValid,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.save_action))
        }
    }
}

@Composable
fun CreateWorkoutInputForm(
    workoutDetails: WorkoutDetails,
    modifier: Modifier = Modifier,
    onValueChange: (WorkoutDetails) -> Unit = {},
    enabled: Boolean = true
) {
    val focus = LocalFocusManager.current
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
    ) {
        OutlinedTextField(
            value = workoutDetails.title,
            onValueChange = { onValueChange(workoutDetails.copy(title = it)) },
            label = { Text(stringResource(R.string.create_workout_title_label)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focus.moveFocus(FocusDirection.Down) }
            ),
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = workoutDetails.description,
            onValueChange = { onValueChange(workoutDetails.copy(description = it)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focus.moveFocus(FocusDirection.Down) }
            ),
            label = { Text(stringResource(R.string.create_workout_description_label)) },
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 250.dp),
            enabled = enabled,
        )
        OutlinedTextField(
            value = workoutDetails.date,
            onValueChange = { onValueChange(workoutDetails.copy(date = it)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = { Text(stringResource(R.string.workout_rating_title)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        if (enabled) {
            Text(
                text = stringResource(R.string.required_fields),
                modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_small))
            )
        }
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(
                onClick = {

                },
            ) {
                Icon(imageVector = Icons.Default.Info, contentDescription = "Info")
            }
            Text(text = "Tap check in the top right to save workout")
        }
    }
}

@Composable
fun ColorView() {
    Box(
        modifier = Modifier
            .width(5.dp)
            .fillMaxHeight()
            .padding(start = 10.dp)
            .background(colorResource(id = R.color.select_color))
    )
}
@Preview()
@Composable
private fun CreateWorkoutScreenPreview() {
    TrackItOfficialTheme {
        CreateWorkoutBody(workoutUiState = WorkoutUiState(
            WorkoutDetails(
                title = "Item name", description = "10.00", date = "5"
            )
        ), onItemValueChange = {}, onSaveClick = {})
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


