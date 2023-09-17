package com.example.uiarchitecture

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.uiarchitecture.ui.theme.JetpackComposeUIArchitectureTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeUIArchitectureTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun PlacesPager(modifier: Modifier = Modifier,
                pagerState: Any,
                state: PlacesState,
                onFavoritesButtonClick: (Place) -> Unit,
                onNavigateToPlaceClick: (Place) -> Unit) {

}

@Composable
fun PlacesScreen(
    state: PlacesState,
    action: PlacesAction
) {
    Scaffold {
        PlacesPager(
            pagerState = pagerState,
            state = state,
            onFavoritesButtonClick = action.,
            onNavigateToPlaceClick =  {place:Place -> onAction(PlacesAction.NavigateToButtonClicked(place)}
        )
    }
}

@Composable
fun rememberPlacesActions(navController: NavController): PlacesAction {

}

private fun Context.showError() {
    Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT)
}

@Composable
fun PlacesRoute(
    navController: NavController,
    viewModel: PlacesViewModel = PlacesViewModel(),
) {
    val state by viewModel.stateFlow.collectAsState()
    val context = LocalContext.current
    val actions = rememberPlacesActions(navController)

    LaunchedEffect(state.error) {
        state.error?.let {
            context.showError()
            viewModel.dismissError()
        }
    }

    PlacesScreen(
        state : PlacesState = state,
        onFavoritesButtonClick = {},
        onNavigateToPlaceClick = {
            when {
                permissionState.isGranted -> {
                    analyitcs.track("StartRoutePlanner")
                    navController.navigate("RoutePlanner")
                }
                permissionState.shouldShowRationale -> {
                    analytics.track("RationaleShown")
                    navController.navigate("LocationRationale")
                }
                else -> {
                    permissionState.launchPermissionRequest()
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackComposeUIArchitectureTheme {
        Greeting("Android")
    }
}