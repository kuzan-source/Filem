package com.espiralsoft.filem.domain.repository

import com.espiralsoft.filem.domain.model.FileEntity
import java.nio.file.Path

interface FileRepository {
    suspend fun createFile(dirPath: Path, name: String): Boolean // Create
    suspend fun getFiles(dirPath: Path): List<FileEntity> // Read
    suspend fun renameFile(filePath: Path, newName: String): Boolean // Update
    suspend fun moveFile(filePath: Path, targetDir: Path): Boolean // Update
    suspend fun copyFile(filePath: Path, targetDir: Path): Boolean // Update
    suspend fun deleteFile(filePath: Path): Boolean // Delete

}