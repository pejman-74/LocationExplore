package com.locationexplorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.locationexplorer.ui.screen.explorerscreen.ExplorerScreen
import com.locationexplorer.ui.screen.explorerscreen.ExplorerScreenViewModel
import com.locationexplorer.ui.theme.LocationExplorerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LocationExplorerTheme {
                val explorerScreenViewModel: ExplorerScreenViewModel = viewModel()
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                Scaffold(scaffoldState = scaffoldState, modifier = Modifier.fillMaxSize()) {
                    NavHost(
                        navController = navController,
                        startDestination = EXPLORER_SCREEN_ROUTE
                    ) {
                        composable(EXPLORER_SCREEN_ROUTE) {
                            ExplorerScreen(explorerScreenViewModel, { message ->
                                scaffoldState.snackbarHostState.showSnackbar(message)
                            }) { venueId: String ->

                            }
                        }
                    }
                }
            }

        }
    }
}

