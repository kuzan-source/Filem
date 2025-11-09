package com.espiralsoft.filem.di

import com.espiralsoft.filem.data.local.LocalDataSource
import com.espiralsoft.filem.data.repository.LocalDataRepositoryImpl
import com.espiralsoft.filem.domain.repository.DirectoryRepository
import com.espiralsoft.filem.domain.repository.FileRepository
import com.espiralsoft.filem.domain.usecase.DirectoryUseCases
import com.espiralsoft.filem.domain.usecase.FileUseCases
import com.espiralsoft.filem.domain.usecase.GetDirectoriesUseCase
import com.espiralsoft.filem.domain.usecase.GetFilesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Proveer la fuente de datos local
    @Provides
    @Singleton
    fun provideLocalDataSource(): LocalDataSource {
        return LocalDataSource()
    }

    // Proveer el repositorio principal
    // Implementa ambas interfaces: DirectoryRepository y FileRepository
    @Provides
    @Singleton
    fun provideLocalDataRepository(
        localDataSource: LocalDataSource
    ): LocalDataRepositoryImpl {
        return LocalDataRepositoryImpl(localDataSource)
    }

    // Proveerlo también como sus interfaces del dominio
    @Provides
    fun provideFileRepository(
        repositoryImpl: LocalDataRepositoryImpl
    ): FileRepository = repositoryImpl

    @Provides
    fun provideDirectoryRepository(
        repositoryImpl: LocalDataRepositoryImpl
    ): DirectoryRepository = repositoryImpl

    // Proveer los casos de uso agrupados
    @Provides
    @Singleton
    fun provideFileUseCases(
        fileRepository: FileRepository,
    ): FileUseCases {
        return FileUseCases(
            getFiles = GetFilesUseCase(fileRepository),
            //getDirectories = GetDirectoriesUseCase(directoryRepository),
            //createFile = CreateFileUseCase(fileRepository),
            //createDirectory = CreateDirectoryUseCase(directoryRepository),
            //deleteFile = DeleteFileUseCase(fileRepository),
            //deleteDirectory = DeleteDirectoryUseCase(directoryRepository),
            //renameFile = RenameFileUseCase(fileRepository),
            //renameDirectory = RenameDirectoryUseCase(directoryRepository),
            //moveFile = MoveFileUseCase(fileRepository),
            //moveDirectory = MoveDirectoryUseCase(directoryRepository),
            //copyFile = CopyFileUseCase(fileRepository),
            //copyDirectory = CopyDirectoryUseCase(directoryRepository)
        )
    }

    // Proveer los casos de uso agrupados
    @Provides
    @Singleton
    fun provideDirectoryUseCases(
        directoryRepository: DirectoryRepository
    ): DirectoryUseCases {
        return DirectoryUseCases(
            getDirectories = GetDirectoriesUseCase(directoryRepository),
            //getDirectories = GetDirectoriesUseCase(directoryRepository),
            //createFile = CreateFileUseCase(fileRepository),
            //createDirectory = CreateDirectoryUseCase(directoryRepository),
            //deleteFile = DeleteFileUseCase(fileRepository),
            //deleteDirectory = DeleteDirectoryUseCase(directoryRepository),
            //renameFile = RenameFileUseCase(fileRepository),
            //renameDirectory = RenameDirectoryUseCase(directoryRepository),
            //moveFile = MoveFileUseCase(fileRepository),
            //moveDirectory = MoveDirectoryUseCase(directoryRepository),
            //copyFile = CopyFileUseCase(fileRepository),
            //copyDirectory = CopyDirectoryUseCase(directoryRepository)
        )
    }

}
