package com.espiralsoft.filem.presentation.viewmodel

import com.espiralsoft.filem.domain.usecase.DirectoryUseCases
import com.espiralsoft.filem.domain.usecase.FileUseCases
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.espiralsoft.filem.presentation.state.FileListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.nio.file.Path
import javax.inject.Inject

/**
 * ViewModel para la pantalla de explorador de directorios
 */
@HiltViewModel
class FileListViewModel @Inject constructor(
    private val directoryUseCases: DirectoryUseCases,
    private val fileUseCases: FileUseCases
) : ViewModel() {

    private val pathStack: MutableList<Path> = mutableListOf() // Pila de rutas
    private val _state: MutableStateFlow<FileListState> = MutableStateFlow(FileListState()) // Estado de la pantalla
    val state: StateFlow<FileListState> get() = _state // Estado publico de la pantalla

    //  Carga el contenido del directorio especificado
    private fun loadPath(pathDirectory: Path) {
        viewModelScope.launch(Dispatchers.IO) {

            _state.value = _state.value.copy(
                isLoading = true
            )

            val rootFiles: List<Path> = fileUseCases.getFiles(pathDirectory) // LIsta de archivos de root
            val rootDirectories: List<Path> = directoryUseCases.getDirectories(pathDirectory) // Lista de directorios de root

            // Actualizar el estado de la pantalla
            _state.value = _state.value.copy(
                directories = rootDirectories,
                files = rootFiles,
                isLoading = false
            )

        }
    }

    //  Inicializa la pantalla con el directorio especificado
    fun initWithPath(pathDirectory: Path) {
        if (pathStack.isEmpty()) {
            pathStack.add(pathDirectory)
            loadPath(pathDirectory)
        }
    }

    //  Navega hacia atrás en la pila de rutas
    fun navigateBack(): Boolean {
        if (pathStack.size <= 1) return false
        pathStack.removeAt(pathStack.lastIndex)
        loadPath(pathStack.last())
        return true
    }

    //  Navega hacia un subdirectorio
    fun navigateTo(pathDirectory: Path) {
        pathStack.add(pathDirectory)
        loadPath(pathDirectory)
    }

    //  Selecciona o deselecciona un elemento
    fun toggleSelection(itemPath: Path) {
        val current = _state.value.selectedItems.toMutableSet()
        if (current.contains(itemPath)) {
            current.remove(itemPath)
        } else {
            current.add(itemPath)
        }
        _state.value = _state.value.copy(selectedItems = current)
    }
}