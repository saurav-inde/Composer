package com.example.compose.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.compose.viewmodel.NotesViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesHomeScreen(navController: NavController, viewModel: NotesViewModel = hiltViewModel()) {
    val notes by viewModel.notes.collectAsState(initial = emptyList())
    val selectedNotes by viewModel.selectedNotes.collectAsState()
    val selectionMode = selectedNotes.isNotEmpty()


    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(

        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(
                    "Menu",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(16.dp)
                )
                ActionRow(
                    "Notes",
                    Icons.Outlined.Create
                )
                ActionRow(
                    "Archived",
                    Icons.Outlined.Done
                )
                ActionRow(
                    "Recycle Bin",
                    Icons.Default.Delete
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    actions = {
                        if (selectionMode) {
                            IconButton(onClick = { viewModel.deleteSelected() }) {
                                Icon(Icons.Default.Delete, contentDescription = "Delete")
                            }
                        }
                    },
                    title = { Text("Composer") },
                    navigationIcon = {
                        if (selectionMode) {
                            IconButton(onClick = { viewModel.clearSelection() }) {
                                Icon(Icons.Default.Close, contentDescription = "Cancel")
                            }
                        } else {

                            IconButton(onClick = {
                                scope.launch { drawerState.open() }   // ✅ Opens the drawer
                            }) {
                                Icon(Icons.Default.Menu, contentDescription = "Menu")
                            }
                        }
                    }


                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        navController.navigate("details")
                    }) {
                    Icon(Icons.Default.Add, contentDescription = "Add")

                }
            },


            ) { innerPadding ->
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 150.dp), // auto-fit grid
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(
                    start = 16.dp,
                    end = 16.dp,
                    top = innerPadding.calculateTopPadding() + 16.dp,
                    bottom = innerPadding.calculateBottomPadding() + 16.dp
                )
            ) {
                items(notes.size) { idx ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .clip(
                                RoundedCornerShape(12.dp)
                            )
                            .background(color = MaterialTheme.colorScheme.surfaceVariant)
                            .combinedClickable(
                                onClick = { navController.navigate("details") },
                                onLongClick = {
                                    viewModel.toggleSelection(notes[idx].id)
                                }
                            ),

                        // square-ish cards
                        //                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        contentAlignment = Alignment.TopStart

                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(12.dp),

                            ) {
                            Column {
                                Text(
                                    text = notes[idx].title,
                                    maxLines = 2, overflow = TextOverflow.Ellipsis,
                                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                                )

                                Spacer(modifier = Modifier.height(12.dp))
                                Text(
                                    text = notes[idx].content,
                                    overflow = TextOverflow.Ellipsis,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }
                }
            }
        }
    }


}

@Composable
fun ActionRow(
    title: String,
    icon: ImageVector,

    ) {
    Row(
        Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            icon,
            contentDescription = title, Modifier.size(24.dp)
        )
        Spacer(Modifier.width(16.dp))
        Text(title)
    }
}
