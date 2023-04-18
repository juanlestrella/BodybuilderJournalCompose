package com.example.bodybuilder.data.DailyCalorieData

data class DailyCalorieGoalsData(
    val maintain: Number?,
    val mildWeightLoss: LossWeightData?,
    val weightLoss: LossWeightData?,
    val extremeWeightLoss: LossWeightData?,
    val mildWeightGain: GainWeightData?,
    val weightGain: GainWeightData?,
    val extremeWeightGain: GainWeightData?
)
