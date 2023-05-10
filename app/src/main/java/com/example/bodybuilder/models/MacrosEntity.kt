package com.example.bodybuilder.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bodybuilder.data.MacrosData.MacrosDetail

@Entity(tableName = "MacrosEntity")
data class MacrosEntity(
    var calorie: Float?,
    var balanced: MacrosDetail?,
    var lowFat: MacrosDetail?,
    var lowCarbs: MacrosDetail?,
    var highProtein: MacrosDetail?
){
    @PrimaryKey(autoGenerate = true) var idMacros: Int = 0
}