/**
 * State para la pantalla de explorador de directorios
 */

package com.espiralsoft.filem.presentation.state

import java.nio.file.Path

data class FileListState(
    override val files: List<Path> = emptyList(), // Lista de archivos disponibles
    override val directories: List<Path> = emptyList(), // Lista de directorios disponibles
    override val isLoading: Boolean = false, // Estado de carga
    override val selectedItems: Set<Path> = emptySet(), // Lista de elementos seleccionados
    //val currentPath: Path // Ruta actual
) : DirectoryViewState
