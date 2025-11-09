package com.espiralsoft.filem.domain.repository

import java.nio.file.Path

interface FileRepository {
    suspend fun createFile(dirPath: Path, name: String): Boolean // Create
    suspend fun getFiles(dirPath: Path): List<Path> // Read
    suspend fun renameFile(filePath: Path, newName: String): Boolean // Update
    suspend fun moveFile(filePath: Path, targetDir: Path): Boolean // Update
    suspend fun copyFile(filePath: Path, targetDir: Path): Boolean // Update
    suspend fun deleteFile(filePath: Path): Boolean // Delete

}