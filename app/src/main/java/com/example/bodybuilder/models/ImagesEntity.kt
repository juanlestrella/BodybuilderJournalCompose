package com.example.bodybuilder.models

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ImagesEntity")
data class ImagesEntity(
    val imagesString: String,
    @PrimaryKey(autoGenerate = true)
    @NonNull
    val id_images: Int = 0
)
