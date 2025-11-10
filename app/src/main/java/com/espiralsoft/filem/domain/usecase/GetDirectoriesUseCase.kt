package com.espiralsoft.filem.domain.usecase

import com.espiralsoft.filem.domain.repository.DirectoryRepository
import java.nio.file.Path
import javax.inject.Inject

class GetDirectoriesUseCase @Inject constructor(
    private val repository: DirectoryRepository
) {
    suspend operator fun invoke(path: Path): List<Path> {
        return repository.getDirectories(path)
    }
}
