package com.example.bodybuilder.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bodybuilder.data.BmiData.BmiData

@Entity(tableName = "BmiEntity")
data class BmiEntity(
//    val bmi: Float?,
//    val health: String?,
//    val healthy_bmi_range: String?,
    val bmi: BmiData,
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
)
