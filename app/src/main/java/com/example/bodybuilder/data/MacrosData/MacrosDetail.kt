package com.example.bodybuilder.data.MacrosData

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
data class MacrosDetail(
    var protein: Float?,
    var fat: Float?,
    var carbs: Float?
)
