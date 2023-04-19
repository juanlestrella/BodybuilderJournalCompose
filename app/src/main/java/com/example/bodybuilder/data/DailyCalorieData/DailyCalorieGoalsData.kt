package com.example.bodybuilder.data.DailyCalorieData

import com.squareup.moshi.Json

data class DailyCalorieGoalsData(
    @Json(name = "maintain weight")
    val maintain: Int?,
    @Json(name = "Mild weight loss")
    val mildWeightLoss: LossWeightData?,
    @Json(name = "Weight loss")
    val weightLoss: LossWeightData?,
    @Json(name = "Extreme weight loss")
    val extremeWeightLoss: LossWeightData?,
    @Json(name = "Mild weight gain")
    val mildWeightGain: GainWeightData?,
    @Json(name = "Weight gain")
    val weightGain: GainWeightData?,
    @Json(name = "Extreme weight gain")
    val extremeWeightGain: GainWeightData?
)
