package com.espiralsoft.filem.data.repository

import com.espiralsoft.filem.data.local.LocalDataSource
import com.espiralsoft.filem.domain.model.FileEntity
import com.espiralsoft.filem.domain.repository.DirectoryRepository
import com.espiralsoft.filem.domain.repository.FileRepository
import com.espiralsoft.filem.utils.FileType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.nio.file.Path

/**
 * Implementacion del repositorio local para directorios y archivos
 * Actúa como un adaptador de concurrencia que envuelve la llamada real.
 */
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

    /* OTROS */

    private fun detectTypeFromExtension(path: Path): FileType {
        val extension: String = path.toFile().extension.lowercase()
        return when (extension) {
            "jpg", "png", "gif", "bmp", "webp" -> FileType.IMAGE //Imagenes
            "mp3", "wav", "ogg", "flac", "aac" -> FileType.AUDIO //Audio
            "mp4", "avi", "mkv", "mov", "3gp", "webm" -> FileType.VIDEO // Video
            "pdf" -> FileType.PDF // Pdf
            "doc", "docx", "txt", "rtf", "odt" -> FileType.TEXTO // Documentos
            "exel" -> FileType.CALCULO // Documentos de calculo
            "csv", "json", "xml", "bin", "dat" -> FileType.TEXTOPLANO // Archivos de datos
            "exe" -> FileType.EJECUTABLES // Ejecutables
            "zip", "rar", "7z", "tar", "gz" -> FileType.COMPRIMIDOS // Comprimidos
            "html" -> FileType.WEB // Internen
            "apk" -> FileType.APK //Apks
            else -> FileType.UNKNOWN // Desconicidos u otros
        }
    }
}
