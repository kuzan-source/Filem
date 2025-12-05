package com.espiralsoft.filem.presentation.viewmodel

import com.espiralsoft.filem.domain.usecase.DirectoryUseCases
import com.espiralsoft.filem.domain.usecase.FileUseCases
import com.espiralsoft.filem.presentation.state.FileListState
import com.espiralsoft.filem.domain.model.FileEntity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
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
    fun loadPath(pathDirectory: Path) {
        viewModelScope.launch {

            _state.value = _state.value.copy(
                isLoading = true
            )

            //¿Agregar un "coroutineScope" para ambas operaciones?
            val dirs: List<Path> = directoryUseCases.getDirectories(pathDirectory)
            val files: List<FileEntity> = fileUseCases.getFiles(pathDirectory)

            _state.value = _state.value.copy(
                directories = dirs,
                files = files,
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