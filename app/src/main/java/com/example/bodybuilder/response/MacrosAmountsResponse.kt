package com.example.bodybuilder.response

import com.example.bodybuilder.data.MacrosData.MacrosData

data class MacrosAmountsResponse(
    val status_code: Int,
    val request_result: String,
    val data: MacrosData
)
