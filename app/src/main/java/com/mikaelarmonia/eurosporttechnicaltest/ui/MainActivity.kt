package com.mikaelarmonia.eurosporttechnicaltest.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.mikaelarmonia.feed.ui.screen.FeedScreen
import com.mikaelarmonia.ui.screen.PopBack
import com.mikaelarmonia.ui.screen.repository.DestinationRepository
import com.mikaelarmonia.ui.theme.EurosportTechnicalTestTheme
import com.mikaelarmonia.ui.toolbar.TopBar
import com.mikaelarmonia.ui.toolbar.TopBarState
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EurosportTechnicalTestTheme {
                var topBarState by remember { mutableStateOf(TopBarState()) }
                val topBarConfigurator: @Composable (TopBarState) -> Unit = { newConfiguration ->
                    topBarState = newConfiguration
                }
                val navController = rememberNavController()
                val destinationRepository: DestinationRepository = remember {
                    get<DestinationRepository>()
                }

                LaunchedEffect("Screen change") {
                    launch {
                        destinationRepository.streamDestination().collect { screen ->
                            when (screen) {
                                PopBack -> navController.popBackStack()
                                else -> navController.navigate(screen.destination())
                            }
                        }
                    }
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        if (topBarState.backgroundColor != Color.Unspecified) {
                            TopBar(
                                title = topBarState.title,
                                backgroundColor = topBarState.backgroundColor,
                                isBackEnabled = topBarState.isBackEnabled,
                                actions = topBarState.actions,
                            )
                        }
                    }
                ) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.surfaceVariant
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = FeedScreen.baseRoute
                        ) {
                            eurosportTTNavGraph(
                                modifier = Modifier
                                    .padding(it)
                                    .fillMaxSize(),
                                navController = navController,
                                topBarConfigurator = topBarConfigurator,
                            )
                        }
                    }
                    if (topBarState.backgroundColor == Color.Unspecified) {
                        TopBar(
                            title = topBarState.title,
                            backgroundColor = topBarState.backgroundColor,
                            isBackEnabled = topBarState.isBackEnabled,
                            actions = topBarState.actions,
                        )
                    }
                }
            }
        }
    }
}
