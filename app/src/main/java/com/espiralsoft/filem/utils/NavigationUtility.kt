package com.espiralsoft.filem.utils

import android.net.Uri
import android.os.Environment
import androidx.navigation.NavType
import androidx.navigation.navArgument
import java.nio.file.Path

/**
 * Ayuda para controlar y formar rutas de navegación
 */
object NavigationUtility {

    fun getRootPath(): Path = Environment.getExternalStorageDirectory().toPath()

    object Hub {
        const val ROUTE: String = "hub"
    }

    // Objeto para controlar la navegación del explorador de archivos
    object Explorer {

        private const val BASE: String = "explorer"
        const val ROUTE: String = "$BASE/{encodedPath}"

        // Construye una ruta de navegación para el explorador de archivos
        fun createRoute(path: String): String {
            return "$BASE/${Uri.encode(path)}"
        }

        fun navArguments() = listOf(
            navArgument("encodedPath") { type = NavType.StringType }
        )
    }

}