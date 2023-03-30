package com.example.bodybuilder.entities

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "BmiData")
data class BmiData(
    val bmi: String,
    val weight: Float,
    val height: Float,
    val weightCategory: String
) : Parcelable
