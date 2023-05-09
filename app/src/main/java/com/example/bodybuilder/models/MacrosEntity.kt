package com.example.bodybuilder.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bodybuilder.data.MacrosData.MacrosDetail

@Entity(tableName = "MacrosEntity")
data class MacrosEntity(
    val calorie: Float?,
    val balanced: MacrosDetail?,
    val lowFat: MacrosDetail?,
    val lowCarbs: MacrosDetail?,
    val highProtein: MacrosDetail?,
    @PrimaryKey(autoGenerate = true) val idMacros: Int = 0
)