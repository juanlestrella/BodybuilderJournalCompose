package com.example.bodybuilder.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bodybuilder.data.DailyCalorieData.DailyCalorieGoalsData

@Entity(tableName = "DailyCalorieEntity")
data class DailyCalorieEntity(
    val BMR: Int?,
    val goals: DailyCalorieGoalsData?,
    @PrimaryKey(autoGenerate = true) val idDailyCalorie: Int = 0
)
