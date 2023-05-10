package com.example.bodybuilder

import androidx.room.TypeConverter
import com.example.bodybuilder.data.DailyCalorieData.DailyCalorieGoalsData
import com.example.bodybuilder.data.DailyCalorieData.GainWeightData
import com.example.bodybuilder.data.DailyCalorieData.LossWeightData
import com.example.bodybuilder.data.MacrosData.MacrosDetail
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun fromMacrosDetail(value : String?) : MacrosDetail? {
        return value?.let { MacrosDetail(it.toFloat(),it.toFloat(),it.toFloat()) }
    }

    @TypeConverter
    fun toMacrosDetail(macrosDetail: MacrosDetail?) : String? {
        return macrosDetail?.let { it.toString() }
    }

    @TypeConverter
    fun fromDailyCalorieGoals(value: String?) : DailyCalorieGoalsData?{
        val lossWeightData = LossWeightData("", 0)
        val gainWeightData = GainWeightData("", 0)
        return value?.let { DailyCalorieGoalsData(0, lossWeightData,lossWeightData,lossWeightData,gainWeightData,gainWeightData,gainWeightData) }
    }

    @TypeConverter
    fun toDailyCalorieGoals(dailyCalorieGoalsData: DailyCalorieGoalsData?) : String?{
        return dailyCalorieGoalsData?.let { it.toString() }
    }
}