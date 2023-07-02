package com.mikaelarmonia.eurosporttechnicaltest.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.mikaelarmonia.feed.ui.screen.FeedScreen
import com.mikaelarmonia.ui.theme.EurosportTechnicalTestTheme
import org.koin.android.ext.android.get

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EurosportTechnicalTestTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surfaceVariant
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = FeedScreen.baseRoute
                    ) {
                        eurosportTTNavGraph(
                            modifier = Modifier.fillMaxSize(),
                            navController = navController,
                            destinationRepository = get(),
                            coroutineScope = lifecycleScope
                        )
                    }
                }
            }
        }
    }
}
