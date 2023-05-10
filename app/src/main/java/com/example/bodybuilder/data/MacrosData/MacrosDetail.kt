package com.example.bodybuilder.data.MacrosData

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
data class MacrosDetail(
    var protein: Float?,
    var fat: Float?,
    var carbs: Float?
) : Parcelable
