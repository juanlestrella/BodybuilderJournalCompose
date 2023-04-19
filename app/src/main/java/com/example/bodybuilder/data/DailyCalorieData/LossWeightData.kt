package com.example.bodybuilder.data.DailyCalorieData

import com.squareup.moshi.Json

data class LossWeightData(
    @Json(name = "loss weight")
    var lossWeight: String?,
    @Json(name = "calory")
    var calory: Int?
)
