package com.espiralsoft.filem.data.repository

import com.espiralsoft.filem.data.local.LocalDataSource
import com.espiralsoft.filem.domain.repository.DirectoryRepository
import com.espiralsoft.filem.domain.repository.FileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.nio.file.Path

class LocalDataRepositoryImpl(
    private val localDataSource: LocalDataSource
) : DirectoryRepository, FileRepository {
    override suspend fun createDirectory(
        dirPath: Path,
        nameDir: String
    ): Boolean {
        TODO("Not yet implemented")
    }

    // Obtener los directorios de un path
    override suspend fun getDirectories(dirPath: Path): List<Path> {
        // Cambia al hilo de I/O
        return withContext(Dispatchers.IO) {
            localDataSource.getDirectories(dirPath)
        }
    }

    override suspend fun renameDirectory(
        dirPath: Path,
        newNameDir: String
    ): Boolean {
        TODO("Not yet implemented")
    }
    override suspend fun moveDirectory(
        dirPath: Path,
        targetDir: Path
    ): Boolean {
        TODO("Not yet implemented")
    }
    override suspend fun copyDirectory(
        dirPath: Path,
        targetDir: Path
    ): Boolean {
        TODO("Not yet implemented")
    }
    override suspend fun deleteDirectory(dirPath: Path): Boolean {
        TODO("Not yet implemented")
    }
    override suspend fun createFile(
        dirPath: Path,
        name: String
    ): Boolean {
        TODO("Not yet implemented")
    }

    // Obtener los archivos de un path
    override suspend fun getFiles(dirPath: Path): List<Path> {
        // Cambia al hilo de I/O
        return withContext(Dispatchers.IO) {
            localDataSource.getFiles(dirPath)
        }
    }

    override suspend fun renameFile(
        filePath: Path,
        newName: String
    ): Boolean {
        TODO("Not yet implemented")
    }
    override suspend fun moveFile(
        filePath: Path,
        targetDir: Path
    ): Boolean {
        TODO("Not yet implemented")
    }
    override suspend fun copyFile(
        filePath: Path,
        targetDir: Path
    ): Boolean {
        TODO("Not yet implemented")
    }
    override suspend fun deleteFile(filePath: Path): Boolean {
        TODO("Not yet implemented")
    }
}