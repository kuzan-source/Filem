/**
 * Representa el icono de un archivo(FileIcon),
 * El icono depende del tipo de archivo
 */
package com.espiralsoft.filem.presentation.ui.components

import com.espiralsoft.filem.domain.model.FileEntity
import com.espiralsoft.filem.utils.FileType
import com.espiralsoft.filem.R
import com.espiralsoft.filem.utils.VideoThumbnailGenerator
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.Bitmap
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.error
import coil3.request.placeholder

/**
* Representa el icono de un archivo
* El icono depende del tipo de archivo
 */

/*
* NOTA: Tengo que cambiar la forma en que se generan las minis para los videos e imagenes
* no me gusta como lo hice -_-
* Usar memoria cache o algo asi para optimizar el rendimiento, tambien que sea asincrono
* Espero poder cambiar esto y no dejarlo asi solamente porque funciona ¿verdad?
 */
@Composable
fun FileIcon(fileEntity: FileEntity) {

    when (fileEntity.fileType) {

        FileType.IMAGE -> { // Es una imagen, usa Coil para cargar la miniatura
            val painter: AsyncImagePainter = rememberAsyncImagePainter(
                model =  ImageRequest.Builder(LocalContext.current)
                    .data(fileEntity.path) // Carga la imagen desde el archivo
                    .crossfade(true) // Animación de transicion
                    .placeholder(fileEntity.fileType.iconRes) // Muestra un icono para la carga
                    .error(R.drawable.icon_error)
                    .size(128) // Tamaño de la miniatura
                    .build() // Construye la solicitud
            )
            Image(
                painter = painter,
                contentDescription = "Miniatura de imagen",
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
        }
        FileType.VIDEO -> { // Es un video. Generar miniatura
            val bmp : Bitmap? = VideoThumbnailGenerator.generate(fileEntity.path)
            if (bmp != null) {
                Image(
                    bitmap = bmp.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier.size(48.dp).clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
            } else {
                Image(
                    painter = painterResource(id = fileEntity.fileType.iconRes),
                    contentDescription = "Ícono de archivo",
                    modifier = Modifier.size(32.dp)
                )
            }

        }
        else -> {
            // Otro tipo de archivo, muestra el icono
            Image(
                painter = painterResource(id = fileEntity.fileType.iconRes),
                contentDescription = "Ícono de archivo",
                modifier = Modifier.size(32.dp)
            )
        }
    }

}
