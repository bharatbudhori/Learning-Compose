package com.example.unitconverter.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.unitconverter.Routes
import com.example.unitconverter.services.LocationUtils
import com.example.unitconverter.viewModels.LocationViewModel

@Composable
fun ShoppingList(
    navController: NavHostController,
    viewModel : LocationViewModel
) {
    val context = LocalContext.current
    var locationUtils = LocationUtils(context)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        ShoppingListApp(
            navController = navController,
            context = context,
            address = viewModel.address.value.firstOrNull()?.formatted_address ?: "No Address",
            locationUtils = locationUtils,
            viewModel = viewModel
        )
    }
}

