package com.example.bodybuilder.data.MacrosData

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MacrosData(
    var calorie: Float?,
    var balanced: MacrosDetail,
    var lowFat: MacrosDetail,
    var lowCarbs: MacrosDetail,
    var highProtein: MacrosDetail
)