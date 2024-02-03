package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.unitconverter.models.Category
import com.example.unitconverter.screens.CategoryDetailScreen
import com.example.unitconverter.screens.CounterApp
import com.example.unitconverter.screens.LocationScreen
import com.example.unitconverter.screens.RecipeScreen
import com.example.unitconverter.screens.ShoppingList
import com.example.unitconverter.screens.UnitConverter
import com.example.unitconverter.ui.theme.UnitConverterTheme
import com.example.unitconverter.viewModels.CounterViewModel
import com.example.unitconverter.viewModels.LocationViewModel
import com.example.unitconverter.viewModels.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ScreenMain()
                }
            }
        }
    }
}

@Composable
fun ScreenMain() {

    val navController = rememberNavController()
    val viewModel: CounterViewModel = viewModel()
    val locationViewModel: LocationViewModel = viewModel()
    val recipeViewModel: MainViewModel = viewModel()
    val viewState by recipeViewModel.categoriesState

    NavHost(navController = navController, startDestination = Routes.Home.route) {

        // First route : Home
        composable(Routes.Home.route) {
            UnitConverter(navController = navController)
        }

        // Another Route : Shopping List
        composable(Routes.ShoppingList.route) {
            ShoppingList()
        }

        // Another Route : Counter App
        composable(Routes.CounterApp.route) {
            CounterApp(viewModel)
        }

        // Another Route : Recipe App
        composable(Routes.RecipeApp.route) {
            RecipeScreen(navController = navController, viewState = viewState)
        }

        // Another Route : Recipe Detail
        composable(Routes.RecipeDetail.route) {
            val category =
                navController.previousBackStackEntry?.savedStateHandle?.get<Category>("category")
                    ?: Category("", "", "", "")
            CategoryDetailScreen(category)
        }

        // Another Route : Location Screen
        composable(Routes.LocationScreen.route) {
            LocationScreen(locationViewModel)
        }

    }
}