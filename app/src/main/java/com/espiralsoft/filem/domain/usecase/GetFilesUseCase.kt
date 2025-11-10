package com.espiralsoft.filem.domain.usecase

import com.espiralsoft.filem.domain.repository.FileRepository
import java.nio.file.Path
import javax.inject.Inject

class GetFilesUseCase @Inject constructor(
    private val repository: FileRepository
) {
    suspend operator fun invoke(path: Path): List<Path> {
        return repository.getFiles(path)
    }
}
