package com.example.unitconverter.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.unitconverter.Routes


data class ShoppingListItem(
    val name: String,
    val id: Int,
    var quantity: Int,
    var isEditing: Boolean = false
)

@Composable
fun ShoppingListApp() {
    var sItems by remember {
        mutableStateOf(listOf<ShoppingListItem>())
    }

    var showDialog by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,

        ) {
        Button(
            onClick = {
                showDialog = true
            },
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        ) {
            Text(text = "Add Item")
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(sItems) {

            }
        }

    }

    if (showDialog) {
        AlertDialog(
            icon = {
                Icon(Icons.Default.Info, contentDescription = "Example Icon")
            },
            title = {
                Text(text = "Add Shopping Item")
            },
            text = {

            },
            onDismissRequest = {
                showDialog = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDialog = false
                    }
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDialog = false
                    }
                ) {
                    Text("Dismiss")
                }
            }
        )
    }
}