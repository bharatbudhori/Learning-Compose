package com.example.unitconverter.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.unitconverter.Routes
import com.example.unitconverter.models.Category
import com.example.unitconverter.viewModels.MainViewModel

@Composable
fun RecipeScreen(modifier: Modifier = Modifier, viewState: MainViewModel.RecipeState, navController: NavHostController) {


    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        when {
            viewState.loading -> {
                CircularProgressIndicator(modifier.align(Alignment.Center))
            }

            viewState.error != null -> {
                Text(text = "ERROR OCCURED")
            }

            else -> {
                // Display Categories
                CategoryScreen(categories = viewState.list, navController = navController)
            }
        }
    }

}

@Composable
fun CategoryScreen(categories: List<Category>, navController: NavHostController) {
    LazyVerticalGrid(GridCells.Fixed(2), modifier = Modifier.fillMaxSize()) {
        items(categories){
            category -> CategoryItem(category = category, navController = navController)
        }
    }
}

@Composable
fun CategoryItem(category: Category, navController: NavHostController) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
            .clickable {
                // This part is responsible for passing data from the current screen to the next screen.
                // It utilizes the savedStateHandle, which is a component of the compose navigation library.

                navController.currentBackStackEntry?.savedStateHandle?.set("category", category)
                navController.navigate(Routes.RecipeDetail.route)
            }
            ,

        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(category.strCategoryThumb),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)

        )

        Text(
            text = category.strCategory,
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}