package com.example.bodybuilder.response

import com.example.bodybuilder.data.DailyCalorieData.DailyCalorieData

data class DailyCalorieResponse(
    var status_code: Number,
    var request_result: String,
    var data: DailyCalorieData
)
