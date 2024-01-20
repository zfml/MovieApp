package com.zfml.tmdbapimovie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.zfml.tmdbapimovie.presentation.home.HomeScreen
import com.zfml.tmdbapimovie.presentation.navigation.Screen
import com.zfml.tmdbapimovie.presentation.navigation.SetUpNavGraph
import com.zfml.tmdbapimovie.ui.theme.TMDBAPIMOVIETheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TMDBAPIMOVIETheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                  val navController = rememberNavController()
//                    SetUpNavGraph(
//                        navController = navController,
//                        startDestination = Screen.Home.route
//                    )
                    HomeScreen()
                }
            }
        }
    }
}