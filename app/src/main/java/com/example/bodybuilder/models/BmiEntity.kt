package com.example.bodybuilder.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "BmiEntity")
data class BmiEntity(
    val bmi: Float?,
    val health: String?,
    val healthy_bmi_range: String?,
    @PrimaryKey(autoGenerate = true) val idBmi: Int = 0,
)
