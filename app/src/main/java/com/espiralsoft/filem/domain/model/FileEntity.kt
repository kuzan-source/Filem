package com.espiralsoft.filem.domain.model

import com.espiralsoft.filem.utils.FileType

data class FileEntity(
    val name: String,
    val path: String,
    val size: Long,
    val fileType: FileType,
)
