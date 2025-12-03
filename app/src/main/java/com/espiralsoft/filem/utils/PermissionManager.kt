package com.espiralsoft.filem.utils

import android.content.Context
import android.content.Intent
import android.os.Environment
import android.provider.Settings
import androidx.core.net.toUri

/**
 * Utils para verificar y solicitar permisos
 */
object PermissionUtils {

    /**
     * Verifica si la app tiene permisos para acceder al almacenamiento externo
     */
    fun hasPermissions(): Boolean {
        return Environment.isExternalStorageManager()
    }

    /**
     * Solicita el permiso para Android 11+ para acceso total a archivos
     */
    fun requestAllFilesAccessPermission(context: Context) {
        try {
            val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
            intent.data = "package:${context.packageName}".toUri()
            context.startActivity(intent)
        } catch (e: Exception) {
            // fallback si falla el intent anterior
            val intent = Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION)
            context.startActivity(intent)
        }
    }

}
