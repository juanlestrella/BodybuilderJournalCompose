package com.example.bodybuilder.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "BodyFatEntity")
data class BodyFatEntity(
    val bodyFat: Float?,
    val category: String?,
    val bodyFatMass: Float?,
    val leanBodyMass: Float?,
    val bmi: Float?,
    @PrimaryKey(autoGenerate = true) val idBodyFat: Int = 0
)
