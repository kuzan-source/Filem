package com.espiralsoft.filem.utils

import com.espiralsoft.filem.R

/**
 * Definido los distintos tipos de archivos para abrirlos con la app correcta,
 * caracteristicas iconos dependiendo del tipo, y la accion a realizar al hacer click
 */
enum class FileType(
    val iconRes: Int,
    val mimeType: String
) {
    IMAGE(
        iconRes = R.drawable.icon_img0,
        mimeType = "image/*"
    ),
    AUDIO(
        iconRes = R.drawable.icon_audio0,
        mimeType = "audio/*",
    ),
    VIDEO(
        iconRes = R.drawable.icon_video0,
        mimeType = "video/*",
    ),
    PDF(
        iconRes = R.drawable.icon_pdf0,
        mimeType = "application/pdf",
    ),
    TEXTO(
        iconRes = R.drawable.icon_document0,
        mimeType = "text/*",
    ),
    CALCULO(
        iconRes = R.drawable.icon_sheet0,
        mimeType = "TODO",
    ),
    TEXTOPLANO(
        iconRes = R.drawable.icon_plaintext0,
        "text/*"
    ),
    EJECUTABLES(
        iconRes = R.drawable.icon_exec0,
        mimeType = "TODO",
    ),
    COMPRIMIDOS(
        iconRes = R.drawable.icon_archive0,
        mimeType = "TODO",
    ),
    WEB(
        iconRes = R.drawable.icon_web0,
        mimeType = "TODO",
    ),
    APK(
        iconRes = R.drawable.icon_apk0,
        mimeType = "TODO",
    ),
    UNKNOWN(
        iconRes = R.drawable.icon_unkdown,
        mimeType = "",
    )
}
