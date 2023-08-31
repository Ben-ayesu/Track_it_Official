import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.trackitofficial.R
import com.example.trackitofficial.ui.AppViewModelProvider
import com.example.trackitofficial.ui.create.CreateWorkoutViewModel
import com.example.trackitofficial.ui.navigation.NavigationDestination

object CreateWorkoutEntryDestination : NavigationDestination {
    override val route = "create_workout_entry"
    override val titleRes = R.string.date
}

@Composable
fun CreateWorkoutScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CreateWorkoutViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {

}