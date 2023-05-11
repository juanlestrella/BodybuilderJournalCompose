package com.example.bodybuilder.data.DailyCalorieData

import com.squareup.moshi.Json
import kotlinx.serialization.Serializable

@Serializable
data class GainWeightData(
    @Json(name = "gain weight")
    var gainWeight: String?,
    @Json(name = "calory")
    var calory: Int?
)
