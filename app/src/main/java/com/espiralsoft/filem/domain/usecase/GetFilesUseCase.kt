package com.espiralsoft.filem.domain.usecase

import com.espiralsoft.filem.domain.repository.FileRepository
import java.nio.file.Path

class GetFilesUseCase(
    private val repository: FileRepository
) {
    suspend operator fun invoke(path: Path): List<Path> {
        return repository.getFiles(path)
    }
}
