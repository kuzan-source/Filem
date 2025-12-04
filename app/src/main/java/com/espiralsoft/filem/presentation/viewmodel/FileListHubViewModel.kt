package com.espiralsoft.filem.presentation.viewmodel

import com.espiralsoft.filem.presentation.state.FileListHubState
import com.espiralsoft.filem.domain.usecase.DirectoryUseCases
import com.espiralsoft.filem.domain.usecase.FileUseCases
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.nio.file.Path
import javax.inject.Inject

/**
 * ViewModel para la pantalla principal de explorador de directorios
 */
@HiltViewModel
class FileListHubViewModel @Inject constructor(
    private val directoryUseCases: DirectoryUseCases,
    private val fileUseCases: FileUseCases,
) : ViewModel() {

    // Estado de la pantalla
    private val _state: MutableStateFlow<FileListHubState> = MutableStateFlow(FileListHubState())

    // Estado de la pantalla público
    val state = _state.asStateFlow()

    // Carga de directorios y archivos en root
    fun loadPath(pathRootDirectory: Path) {
        // El path siempre deberia ser el de root
        //¿Es necesario pasar un parametro? No lo creo

        viewModelScope.launch {

            _state.value = _state.value.copy(
                isLoading = true
            )

            //¿Agregar un coroutineScope?
            val dirsRoot: List<Path> = directoryUseCases.getDirectories(pathRootDirectory)
            val filesRoot: List<Path> = fileUseCases.getFiles(pathRootDirectory)

            _state.value = _state.value.copy(
                directories = dirsRoot,
                files = filesRoot,
                isLoading = false
            )

        }
    }
/*
    // Crear un nuevo directorio en root y recargar el contenido
    fun createDirAndReload(newDirName: String): Boolean {
        if (fileManager.createDirectory(internalStorage, newDirName)) {
            loadPath()
            return true
        }
        return false
    }

    fun deleteSelectedItemsAndReload() {
        val toDelete = _state.value.selectedItems
        toDelete.forEach { fileManager.deleteDirectory(it) } // Solo directorios por ahora
        clearSelection()
        loadPath()
    }

    private fun clearSelection() {
        _state.value = _state.value.copy(selectedItems = emptySet())
    }
*/
    fun toggleSelection(itemPath: Path) {
        val current: MutableSet<Path> = _state.value.selectedItems.toMutableSet()
        if (current.contains(itemPath)) {
            current.remove(itemPath)
        } else {
            current.add(itemPath)
        }
        _state.value = _state.value.copy(selectedItems = current)
    }
}