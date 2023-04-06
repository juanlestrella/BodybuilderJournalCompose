package com.example.bodybuilder.entities


data class BmiResponse(
    var status_code: String,
    var request_result: String,
    var data: BmiData
)
