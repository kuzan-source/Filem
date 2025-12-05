/**
 * Pantalla con la lista de directorios y archivos,
 * simpere se utiliza para mostrar los que no esta en el rootPath
 */
package com.espiralsoft.filem.presentation.ui.screens

import com.espiralsoft.filem.presentation.ui.components.DirectoryContent
import com.espiralsoft.filem.presentation.viewmodel.FileListViewModel
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import java.nio.file.Path

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FileListScreen(
    viewModel: FileListViewModel = hiltViewModel(), // HiltViewModel de la pantalla
    initialPath: Path, // Ruta de directorio del que mostrar el contenido
    onNavigateBack: () -> Unit, // ViewModel de la pantalla
) {

    val state by viewModel.state.collectAsState()

    LaunchedEffect(initialPath) {
        viewModel.initWithPath(initialPath)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Filem") },
                navigationIcon = {
                    IconButton(onClick = {
                        if (!viewModel.navigateBack()) {
                            onNavigateBack()
                        }
                    }) {
                        Icon(Icons.Default.KeyboardArrowUp, contentDescription = "Atrás")
                    }
                }
            )
        }
    ) { innerPadding ->
        // Contenido de la pantalla
        DirectoryContent(
            state = state,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            onNavigateTo = { path -> viewModel.navigateTo(path) },
            toggleSelection = { itemPath -> viewModel.toggleSelection(itemPath) },
            //onOpenFile = TODO()
        )

    }
}
