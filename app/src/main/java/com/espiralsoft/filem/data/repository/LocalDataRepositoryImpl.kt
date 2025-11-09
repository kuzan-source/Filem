package com.espiralsoft.filem.data.repository

import com.espiralsoft.filem.data.local.LocalDataSource
import com.espiralsoft.filem.domain.repository.DirectoryRepository
import com.espiralsoft.filem.domain.repository.FileRepository
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
    override suspend fun getDirectories(dirPath: Path): List<Path> {
        return localDataSource.getDirectories(dirPath)
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
    override suspend fun getFiles(dirPath: Path): List<Path> {
        return localDataSource.getFiles(dirPath)
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