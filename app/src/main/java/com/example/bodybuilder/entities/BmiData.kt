package com.example.bodybuilder.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "BmiData")
data class BmiData(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    val bmi: String = "",
    val weight: Float = 0.toFloat(),
    val height: Float = 0.toFloat(),
    val weightCategory: String = ""
)