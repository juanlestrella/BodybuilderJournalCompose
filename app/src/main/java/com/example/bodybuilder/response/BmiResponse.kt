package com.example.bodybuilder.response

import com.example.bodybuilder.data.BmiData.BmiData


data class BmiResponse(
    var status_code: String,
    var request_result: String,
    var data: BmiData
)
