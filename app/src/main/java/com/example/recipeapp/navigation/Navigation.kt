package com.example.recipeapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.recipeapp.navigation.ScreenPath
import com.example.recipeapp.presentation.searchScreen.SearchScreen
import com.example.recipeapp.presentation.searchScreen.SearchScreenViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun Navigator (){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground
    ) {
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = ScreenPath.Home.path){
            composable(ScreenPath.Home.path){
                val viewModel = hiltViewModel<SearchScreenViewModel>()

                SearchScreen(searchScreenViewModel = viewModel)
            }

        }

    }
}