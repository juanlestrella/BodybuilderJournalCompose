package com.example.bodybuilder.data.BodyFatData

import com.squareup.moshi.Json

data class BodyFatData(
    @Json(name = "Body Fat (U.S. Navy Method)")
    val bodyFat: Float?,
    @Json(name = "Body Fat Category")
    val category: String?,
    @Json(name = "Body Fat Mass")
    val bodyFatMass: Float?,
    @Json(name = "Lean Body Mass")
    val leanBodyMass: Float?,
    @Json(name = "Body Fat (BMI method)")
    val bmi: Float?
)
