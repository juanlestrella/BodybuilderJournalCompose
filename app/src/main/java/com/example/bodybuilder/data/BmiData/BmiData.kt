package com.example.bodybuilder.data.BmiData

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "BmiData")
data class BmiData(
    val bmi: Float?,
    val health: String?,
    val healthy_bmi_range: String?,
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
)