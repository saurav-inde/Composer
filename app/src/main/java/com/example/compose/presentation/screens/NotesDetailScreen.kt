package com.example.compose.presentation.screens

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.compose.data.NotesModel
import com.example.compose.viewmodel.NotesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesDetailScreen(navHostController: NavHostController,viewModel: NotesViewModel = hiltViewModel()) {
    var titleTextString by rememberSaveable { mutableStateOf("") }  // ✅ holds the value across recompositions
    var bodyTextString by rememberSaveable { mutableStateOf("") }  // ✅ holds the value across recompositions

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("New Note") },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                if(bodyTextString.isNotEmpty() || titleTextString.isNotEmpty())

                viewModel.addNotes(NotesModel(
            title =     titleTextString,
                content = bodyTextString,
            )) }) {
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