package com.example.bodybuilder.data.MacrosData

data class MacrosData(
    var calorie: Float?,
    var balanced: MacrosDetail?,
    var lowFat: MacrosDetail?,
    var lowCarbs: MacrosDetail?,
    var highProtein: MacrosDetail?
)