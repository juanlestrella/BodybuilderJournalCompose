package com.example.bodybuilder.models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "BmiEntity")
data class BmiEntity(
    val bmi: Float?,
    val health: String?,
    val healthy_bmi_range: String?,
    @PrimaryKey(autoGenerate = true)
    @NonNull
    val id_bmi: Int = 0,
)
