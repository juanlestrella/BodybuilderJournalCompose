package com.example.bodybuilder.entities.BmiData

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "BmiData")
data class BmiData(
    val bmi: Float?,
    val health: String?,
    val healthy_bmi_range: String?,
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
)