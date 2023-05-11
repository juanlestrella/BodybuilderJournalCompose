package com.example.bodybuilder.data.DailyCalorieData

import com.squareup.moshi.Json
import kotlinx.serialization.Serializable

@Serializable
data class LossWeightData(
    @Json(name = "loss weight")
    var lossWeight: String?,
    @Json(name = "calory")
    var calory: Int?
)
