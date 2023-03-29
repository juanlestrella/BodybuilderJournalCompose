package com.example.bodybuilder.entities

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "contentCard")
data class ContentCardData(
    val image: Int,
    val bodyPart: String
) :Parcelable
