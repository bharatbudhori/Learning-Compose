package com.example.unitconverter

sealed class Routes(val route: String) {
    object Home : Routes("home")
    object ShoppingList : Routes("shoppingList")
}