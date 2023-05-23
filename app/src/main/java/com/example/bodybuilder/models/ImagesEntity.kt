package com.example.bodybuilder.models

import android.graphics.Bitmap
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ImagesEntity")
data class ImagesEntity(
    val imagesString: List<String>, // List<Bitmap>
    @PrimaryKey(autoGenerate = true)
    @NonNull
    val idImages: Int = 0
)
