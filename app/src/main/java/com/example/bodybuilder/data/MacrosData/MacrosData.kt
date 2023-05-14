package com.example.bodybuilder.data.MacrosData

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MacrosData(
    var calorie: Float?,
    var balanced: MacrosDetail,
    @Json(name = "lowfat") var lowFat: MacrosDetail,
    @Json(name = "lowcarbs") var lowCarbs: MacrosDetail,
    @Json(name = "highprotein") var highProtein: MacrosDetail
)