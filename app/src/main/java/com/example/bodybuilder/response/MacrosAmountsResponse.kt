package com.example.bodybuilder.response

import com.example.bodybuilder.entities.MacrosAmountData.MacrosAmountData

data class MacrosAmountsResponse(
    val status_code: Int,
    val request_result: String,
    val data: MacrosAmountData
)
