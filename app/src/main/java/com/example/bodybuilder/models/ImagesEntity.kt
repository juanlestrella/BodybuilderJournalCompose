package com.example.bodybuilder.models

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ImagesEntity")
data class ImagesEntity(
    val imagesString: List<String>,
    @PrimaryKey(autoGenerate = true)
    @NonNull
    val idImages: Int = 0
)
