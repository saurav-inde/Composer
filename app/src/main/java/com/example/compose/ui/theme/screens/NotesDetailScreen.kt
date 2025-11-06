package com.example.compose.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesDetailScreen(navHostController: NavHostController) {
    var titleTextString by rememberSaveable { mutableStateOf("") }  // ✅ holds the value across recompositions
    var bodyTextString by rememberSaveable { mutableStateOf("") }  // ✅ holds the value across recompositions

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("New Note") },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /* action */ }) {
                Icon(Icons.Default.Done, contentDescription = "Add")
            }
        },
    )
    { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp) //  custom horizontal padding,

        ) {
            BasicTextField(

                value = titleTextString,
                onValueChange = { newVal -> titleTextString = newVal },
                modifier = Modifier.fillMaxWidth().focusable(enabled = true),
                cursorBrush = SolidColor(MaterialTheme.colorScheme.primary), // ✅ cursor color
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = MaterialTheme.typography.headlineSmall.fontSize
                ),
                maxLines = 2,
                decorationBox = { innerTextField ->
                    // Optional — if you want a placeholder
                    if (titleTextString.isEmpty()) {
                        Text(
                            text = "Title",
                            style = TextStyle(
                                color = Color.Gray,
                                fontSize = MaterialTheme.typography.headlineSmall.fontSize
                            )
                        )
                    }
                    innerTextField() // this actually shows the text
                },

            )
            Spacer(modifier = Modifier.height(20.dp))

            BasicTextField(
                value = bodyTextString,
                onValueChange = { newVal -> bodyTextString = newVal },
                modifier = Modifier.fillMaxWidth(),
                cursorBrush = SolidColor(MaterialTheme.colorScheme.primary), // ✅ cursor color
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize
                ),
                decorationBox = { innerTextField ->
                    // Optional — if you want a placeholder
                    if (bodyTextString.isEmpty()) {
                        Text(
                            text = "Start writing ...",
                            style = TextStyle(
                                color = Color.Gray,
                                fontSize = MaterialTheme.typography.bodyLarge.fontSize
                            )
                        )
                    }
                    innerTextField() // this actually shows the text
                }
            )
        }
    }
}