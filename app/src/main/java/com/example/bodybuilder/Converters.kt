package com.example.bodybuilder

import androidx.room.TypeConverter
import com.example.bodybuilder.data.DailyCalorieData.DailyCalorieGoalsData
import com.example.bodybuilder.data.DailyCalorieData.GainWeightData
import com.example.bodybuilder.data.DailyCalorieData.LossWeightData
import com.example.bodybuilder.data.MacrosData.MacrosDetail
import com.google.gson.Gson
import kotlinx.serialization.json.Json
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString

class Converters {
    val gson = Gson()

    @TypeConverter
    fun toMacrosDetail(macrosDetail: MacrosDetail?) : String {
        return gson.toJson(macrosDetail, MacrosDetail::class.java) ?: ""
    }

    @TypeConverter
    fun fromMacrosDetail(value : String?) : MacrosDetail {
        val nullValue = MacrosDetail(0.toFloat(), 0.toFloat(), 0.toFloat())
        return gson.fromJson(value, MacrosDetail::class.java) ?: nullValue
    }

    @TypeConverter
    fun toDailyCalorieGoals(dailyCalorieGoalsData: DailyCalorieGoalsData?) : String {
        return gson.toJson(dailyCalorieGoalsData, DailyCalorieGoalsData::class.java) ?: ""
    }

    @TypeConverter
    fun fromDailyCalorieGoals(value: String?) : DailyCalorieGoalsData {
        val lossWeightData = LossWeightData("", 0.toFloat())
        val gainWeightData = GainWeightData("", 0.toFloat())
        val nullValue = DailyCalorieGoalsData(0.toFloat(), lossWeightData,lossWeightData,lossWeightData,gainWeightData,gainWeightData,gainWeightData)
        return gson.fromJson(value, DailyCalorieGoalsData::class.java) ?: nullValue
    }

    @TypeConverter
    fun fromNestedList(value: List<List<String>>) : String = Json.encodeToString(value)

    @TypeConverter
    fun toNestedList(value: String) : List<List<String>> = Json.decodeFromString(value) ?: listOf(listOf())

    @TypeConverter
    fun fromList(value: List<String>) : String = Json.encodeToString(value)

    @TypeConverter
    fun toList(value: String) : List<String> =
        try{
            Json.decodeFromString(value) ?: listOf()
        } catch(e: Exception){
            listOf<String>()
        }

    @TypeConverter
    fun toGainWeight(gainWeightData: GainWeightData?) : String {
        return gson.toJson(gainWeightData, DailyCalorieGoalsData::class.java) ?: ""
    }

    @TypeConverter
    fun fromGrainWeight(value : String?) : GainWeightData {
        val nullValue = GainWeightData("", 0.toFloat())
        return gson.fromJson(value, GainWeightData::class.java) ?: nullValue
    }

    @TypeConverter
    fun toLoseWeight(loseWeightData: LossWeightData?) : String {
        return gson.toJson(loseWeightData, DailyCalorieGoalsData::class.java) ?: ""
    }

    @TypeConverter
    fun fromLoseWeight(value : String?) : LossWeightData {
        val nullValue = LossWeightData("", 0.toFloat())
        return gson.fromJson(value, LossWeightData::class.java) ?: nullValue
    }
}