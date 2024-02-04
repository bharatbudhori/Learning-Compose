package com.example.unitconverter

sealed class Routes(val route: String) {
    object Home : Routes("home")
    object ShoppingList : Routes("shoppingList")
    object CounterApp : Routes("counterApp")
    object RecipeApp : Routes("mealApp")
    object RecipeDetail : Routes("recipeDetail")
    object LocationScreen : Routes("locationScreen")
    object MapsScreen : Routes("mapsScreen")
}