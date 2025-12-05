package com.espiralsoft.filem.presentation.state

import com.espiralsoft.filem.domain.model.FileEntity
import java.nio.file.Path

interface DirectoryViewState {
    val files: List<FileEntity> // Lista de archivos disponibles
    val directories: List<Path> // Lista de directorios disponibles
    val isLoading: Boolean // Estado de carga
    val selectedItems: Set<Path> // Lista de elementos seleccionados
}
