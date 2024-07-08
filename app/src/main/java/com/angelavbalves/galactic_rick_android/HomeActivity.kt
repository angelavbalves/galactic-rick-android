package com.angelavbalves.galactic_rick_android

import android.content.res.Resources.Theme
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.angelavbalves.galactic_rick_android.commons.utils.RequestResult
import com.angelavbalves.galactic_rick_android.ui.details.CharacterDetails
import com.angelavbalves.galactic_rick_android.ui.home.CharactersHome
import com.angelavbalves.galactic_rick_android.ui.viewmodels.CharacterDetailsViewModel
import com.angelavbalves.galactic_rick_android.ui.viewmodels.CharactersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    private val viewModel by viewModels<CharactersViewModel>()
    private val detailsViewModel by viewModels<CharacterDetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController, startDestination = "main") {
                composable("main") {
                    CharactersHome(
                        navController = navController,
                        viewModel = viewModel
                    )
                }
                composable(
                    "details/{id}",
                    arguments = listOf(navArgument("id") { type = NavType.IntType })
                ) { backStackEntry ->
                    CharacterDetails(
                        id = backStackEntry.arguments?.getInt("id") ?: 0,
                        viewModel = detailsViewModel,
                        navController = navController
                    )
                }
            }
        }
    }
}