package com.example.unitconverter.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.unitconverter.Routes
import com.example.unitconverter.ui.theme.UnitConverterTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnitConverter(navController: NavHostController) {
    val firstValue = remember {
        mutableStateOf(0.0)
    }

    val secondValue = remember {
        mutableStateOf(0.0)
    }

    val firstUnit = remember {
        mutableStateOf("Centimeter")
    }

    val secondUnit = remember {
        mutableStateOf("Centimeter")
    }

    val showFirstUnit = remember {
        mutableStateOf(false)
    }

    val showSecondUnit = remember {
        mutableStateOf(false)
    }

    //function to convert the value
    fun convert() {
        if (firstUnit.value == "Centimeter" && secondUnit.value == "Centimeter") {
            secondValue.value = firstValue.value
        } else if (firstUnit.value == "Centimeter" && secondUnit.value == "Meter") {
            secondValue.value = firstValue.value / 100
        } else if (firstUnit.value == "Centimeter" && secondUnit.value == "Feet") {
            secondValue.value = firstValue.value / 30.48
        } else if (firstUnit.value == "Centimeter" && secondUnit.value == "Millimeter") {
            secondValue.value = firstValue.value * 10
        } else if (firstUnit.value == "Meter" && secondUnit.value == "Centimeter") {
            secondValue.value = firstValue.value * 100
        } else if (firstUnit.value == "Meter" && secondUnit.value == "Meter") {
            secondValue.value = firstValue.value
        } else if (firstUnit.value == "Meter" && secondUnit.value == "Feet") {
            secondValue.value = firstValue.value * 3.281
        } else if (firstUnit.value == "Meter" && secondUnit.value == "Millimeter") {
            secondValue.value = firstValue.value * 1000
        } else if (firstUnit.value == "Feet" && secondUnit.value == "Centimeter") {
            secondValue.value = firstValue.value * 30.48
        } else if (firstUnit.value == "Feet" && secondUnit.value == "Meter") {
            secondValue.value = firstValue.value / 3.281
        } else if (firstUnit.value == "Feet" && secondUnit.value == "Feet") {
            secondValue.value = firstValue.value
        } else if (firstUnit.value == "Feet" && secondUnit.value == "Millimeter") {
            secondValue.value = firstValue.value * 304.8
        } else if (firstUnit.value == "Millimeter" && secondUnit.value == "Centimeter") {
            secondValue.value = firstValue.value / 10
        } else if (firstUnit.value == "Millimeter" && secondUnit.value == "Meter") {
            secondValue.value = firstValue.value / 1000
        } else if (firstUnit.value == "Millimeter" && secondUnit.value == "Feet") {
            secondValue.value = firstValue.value / 304.8
        } else if (firstUnit.value == "Millimeter" && secondUnit.value == "Millimeter") {
            secondValue.value = firstValue.value
        }
    }




    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            "Unit Converter",

            style = MaterialTheme.typography.headlineMedium,
            color = Color.Blue
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row {
            OutlinedTextField(value = firstValue.value.toString(), onValueChange = {
                firstValue.value = it.toDoubleOrNull() ?: 0.0
                convert()
            })
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Box {
                Button(onClick = {
                    showFirstUnit.value = !showFirstUnit.value
                }) {
                    Text(firstUnit.value)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                }
                DropdownMenu(expanded = showFirstUnit.value, onDismissRequest = { }) {
                    DropdownMenuItem(text = { Text(text = "Centimeter") }, onClick = {
                        firstUnit.value = "Centimeter"
                        showFirstUnit.value = false
                        convert()
                    })
                    DropdownMenuItem(text = { Text(text = "Meter") }, onClick = {
                        firstUnit.value = "Meter"
                        showFirstUnit.value = false
                        convert()
                    })
                    DropdownMenuItem(text = { Text(text = "Feet") }, onClick = {
                        firstUnit.value = "Feet"
                        showFirstUnit.value = false
                        convert()
                    })
                    DropdownMenuItem(text = { Text(text = "Millimeter") }, onClick = {
                        firstUnit.value = "Millimeter"
                        showFirstUnit.value = false
                        convert()
                    })
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Box {
                Button(onClick = {
                    showSecondUnit.value = !showSecondUnit.value
                }) {
                    Text(secondUnit.value)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                }

                DropdownMenu(expanded = showSecondUnit.value, onDismissRequest = { }) {
                    DropdownMenuItem(text = { Text(text = "Centimeter") }, onClick = {
                        secondUnit.value = "Centimeter"
                        showSecondUnit.value = false
                        convert()
                    })
                    DropdownMenuItem(text = { Text(text = "Meter") }, onClick = {
                        secondUnit.value = "Meter"
                        showSecondUnit.value = false
                        convert()

                    })
                    DropdownMenuItem(text = { Text(text = "Feet") }, onClick = {
                        secondUnit.value = "Feet"
                        showSecondUnit.value = false
                        convert()
                    })
                    DropdownMenuItem(text = { Text(text = "Millimeter") }, onClick = {
                        secondUnit.value = "Millimeter"
                        showSecondUnit.value = false
                        convert()
                    })
                }

            }

        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Result : ${secondValue.value}")


        Spacer(modifier = Modifier.height(36.dp))

        //button to navigate to the shopping list
        Button(onClick = {
            //navigate to the profile screen
            navController.navigate(Routes.ShoppingList.route)
        }) {
            Text(text = "Shopping List")
        }
        Spacer(modifier = Modifier.height(36.dp))

        //button to navigate to the shopping list
        Button(onClick = {
            //navigate to the profile screen
            navController.navigate(Routes.CounterApp.route)
        }) {
            Text(text = "Counter App")
        }

        Spacer(modifier = Modifier.height(36.dp))

        //button to navigate to the shopping list
        Button(onClick = {
            //navigate to the profile screen
            navController.navigate(Routes.RecipeApp.route)
        }) {
            Text(text = "Recipe App")
        }

    }
}

@Preview(showSystemUi = true)
@Composable
fun GreetingPreview() {
    UnitConverterTheme {
        UnitConverter(rememberNavController())
    }
}