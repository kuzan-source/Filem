/**
 * Pantalla principal para
 * mostrar solamente el contenido de la carpeta raiz "/"
 * y accesos como favoritos, tipos de archivos, etc.
 */
package com.espiralsoft.filem.presentation.ui.screens

import com.espiralsoft.filem.presentation.ui.components.DirectoryContent
import com.espiralsoft.filem.presentation.viewmodel.FileListHubViewModel
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import java.nio.file.Path

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FileListHubScreen(
    viewModel: FileListHubViewModel = hiltViewModel(), // HiltViewModel de la pantalla
    onNavigateTo: (Path) -> Unit, // Función para navegar a una ruta
    currentPath: Path
) {

    val state by viewModel.state.collectAsState() // Estado de la pantalla
    /*
    var showDialog by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }

    if (showDeleteDialog) {
        ConfirmDeleteDialog(
            onConfirm = {
                viewModel.deleteSelectedItemsAndReload()
                showDeleteDialog = false
            },
            onDismiss = { showDeleteDialog = false }
        )
    }

    if (showDialog) {
        CreateDirectoryDialog(
            onConfirm = { folderName ->
                viewModel.createDirAndReload(folderName)
                showDialog = false
            },
            onDismiss = { showDialog = false }
        )
    }
*/
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Filem - Inicio") },
                actions = {
                    // Boton para crear un nuevo directorio
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { /* TODO */ },
                        contentAlignment = Alignment.CenterEnd,
                        content = {
                            Icon(
                                Icons.Default.AddCircle,
                                contentDescription = "Más opciones",
                                modifier = Modifier.size(32.dp)
                            )
                        }
                    )
                }
            )
        },
        /*
        bottomBar = {
            BottomActionBar(
                visible = state.selectedItems.isNotEmpty(),
                onDeleteClick = { showDeleteDialog = true }
            )
        }
         */
    ) { innerPadding ->
        // Contenido de la pantalla
        DirectoryContent(
            state = state,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            onNavigateTo = onNavigateTo,
            toggleSelection = { itemPath -> viewModel.toggleSelection(itemPath) }
            // Equivalente a "viewModel::toggleSelection", se puede cambiar
        )

    }

}
