package com.example.bodybuilder.models

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "ImagesEntity")
data class ImagesEntity(
    val Date: String,
    val listImages: List<String>, // List<Bitmap>
    @PrimaryKey(autoGenerate = true)
    val idImages: Int = 0
)
