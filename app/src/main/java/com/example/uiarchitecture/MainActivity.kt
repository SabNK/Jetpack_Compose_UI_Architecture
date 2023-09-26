@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.uiarchitecture

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.uiarchitecture.ui.theme.JetpackComposeUIArchitectureTheme

private const val TAG = "card"
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
                    var text by remember { mutableStateOf("") }
                    var count by remember { mutableStateOf(0) }

                    Column {
                        OutlinedCardWithTitle(
                            title = "OutlinedCard",
                            content = { modifier ->
                                var text by remember { mutableStateOf("") }
                                var ok by remember { mutableStateOf(false) }
                                Column {
                                    Checkbox(
                                        checked = ok,
                                        onCheckedChange = { ok = it },
                                        modifier = modifier.focusable()
                                    )
                                    OutlinedTextField(
                                        value = text,
                                        onValueChange = { text = it },
                                        modifier = modifier
                                    )

                                    Checkbox(
                                        checked = ok,
                                        onCheckedChange = { ok = it },
                                        modifier = modifier.focusable()
                                    )
                                    OutlinedTextField(
                                        value = text,
                                        onValueChange = { text = it },
                                        modifier = modifier
                                    )
                                    Button(onClick = { /*TODO*/ },
                                        modifier = modifier.focusable()) {
                                        Text("Click me")
                                    }
                                }
                            },
                            contentCount = count
                        )
                        OutlinedTextField(
                            value = text,
                            onValueChange = { text = it }
                        )
                        Button(
                            onClick = {
                                count--
                                Log.d(TAG, "onCreate: $count")
                            },
                        ) {
                            Text("Minus")
                        }
                        Button(onClick = { count++
                            Log.d(TAG, "onCreate: $count")},
                        ) {
                            Text("Plus")
                        }

                    }



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
    actions: PlacesActions
) {
    Scaffold {
        PlacesPager(
            modifier = Modifier.padding(it),
            pagerState = Unit,
            state = state,
            onFavoritesButtonClick = actions.onFavoritesClicked,
            onNavigateToPlaceClick = actions.onNavigateToButtonClicked
        )
    }
}

@Composable
fun rememberPlacesActions(coordinator: PlacesCoordinator,
                          ): PlacesActions {
    return remember(coordinator) {
        PlacesActions(
            onNavigateToButtonClicked = {coordinator::navigateToRoutePlanner},
            onFavoritesClicked = {coordinator.viewModel::toggleFavorites}
        )
    }

}

private fun Context.showError() {
    Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT)
}

@Composable
fun PlacesRoute(
    coordinator: PlacesCoordinator
) {
    val state by coordinator.viewModel.stateFlow.collectAsState()
    val context = LocalContext.current
    val actions = rememberPlacesActions(coordinator)

    LaunchedEffect(state.error) {
        state.error?.let {
            context.showError()
            coordinator.viewModel.dismissError()
        }
    }

    PlacesScreen(
        state  = state,
        actions = actions
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackComposeUIArchitectureTheme {
        Greeting("Android")
    }
}