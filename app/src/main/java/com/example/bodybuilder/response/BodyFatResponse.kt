package com.example.bodybuilder.response

import com.example.bodybuilder.data.BodyFatData.BodyFatData

data class BodyFatResponse(
    var status_code: Number,
    var request_result: String,
    var data: BodyFatData
)
