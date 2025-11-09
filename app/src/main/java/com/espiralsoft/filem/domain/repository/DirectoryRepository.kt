package com.espiralsoft.filem.domain.repository

import java.nio.file.Path

interface DirectoryRepository {
    suspend fun createDirectory(dirPath: Path, nameDir: String): Boolean // Create
    suspend fun getDirectories(dirPath: Path): List<Path> // Read
    suspend fun renameDirectory(dirPath: Path, newNameDir: String): Boolean // Update
    suspend fun moveDirectory(dirPath: Path, targetDir: Path): Boolean // Update
    suspend fun copyDirectory(dirPath: Path, targetDir: Path): Boolean // Update
    suspend fun deleteDirectory(dirPath: Path): Boolean // Delete

}