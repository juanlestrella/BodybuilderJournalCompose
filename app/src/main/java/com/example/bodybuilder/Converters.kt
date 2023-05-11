package com.example.bodybuilder

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import com.example.bodybuilder.data.DailyCalorieData.DailyCalorieGoalsData
import com.example.bodybuilder.data.DailyCalorieData.GainWeightData
import com.example.bodybuilder.data.DailyCalorieData.LossWeightData
import com.example.bodybuilder.data.MacrosData.MacrosDetail
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.lang.reflect.Type

class Converters {
    val gson = Gson()

    @TypeConverter
    fun toMacrosDetail(macrosDetail: MacrosDetail?) : String {
        val ans = gson.toJson(macrosDetail, MacrosDetail::class.java) ?: ""
        Log.i("CONVERTER toMacrosDetail", ans)
        return ans
        //return macrosDetail?.let { it.toString() }
    }

    @TypeConverter
    fun fromMacrosDetail(value : String?) : MacrosDetail {
        Log.i("CONVERTER fromMacrosDetail", value ?: "")
        val nullValue = MacrosDetail(0.toFloat(), 0.toFloat(), 0.toFloat())
        return gson.fromJson(value, MacrosDetail::class.java) ?: nullValue
        //return Json.decodeFromString<MacrosDetail>(value as String)
        //return MacrosDetail(0.toFloat(),0.toFloat(),0.toFloat())
    }

    @TypeConverter
    fun toDailyCalorieGoals(dailyCalorieGoalsData: DailyCalorieGoalsData?) : String {
        return gson.toJson(dailyCalorieGoalsData, DailyCalorieGoalsData::class.java) ?: ""
        //return dailyCalorieGoalsData?.let { it.toString() }
    }

    @TypeConverter
    fun fromDailyCalorieGoals(value: String?) : DailyCalorieGoalsData {
        val lossWeightData = LossWeightData("", 0)
        val gainWeightData = GainWeightData("", 0)
        val nullValue = DailyCalorieGoalsData(0, lossWeightData,lossWeightData,lossWeightData,gainWeightData,gainWeightData,gainWeightData)
        return gson.fromJson(value, DailyCalorieGoalsData::class.java) ?: nullValue
        //return value?.let { DailyCalorieGoalsData(0, lossWeightData,lossWeightData,lossWeightData,gainWeightData,gainWeightData,gainWeightData) }
    }

    @TypeConverter
    fun toGainWeight(gainWeightData: GainWeightData?) : String {
        return gson.toJson(gainWeightData, DailyCalorieGoalsData::class.java) ?: ""
    }

    @TypeConverter
    fun fromGrainWeight(value : String?) : GainWeightData {
        val nullValue = GainWeightData("", 0)
        return gson.fromJson(value, GainWeightData::class.java) ?: nullValue
    }

    @TypeConverter
    fun toLoseWeight(loseWeightData: LossWeightData?) : String {
        return gson.toJson(loseWeightData, DailyCalorieGoalsData::class.java) ?: ""
    }

    @TypeConverter
    fun fromLoseWeight(value : String?) : LossWeightData {
        val nullValue = LossWeightData("", 0)
        return gson.fromJson(value, LossWeightData::class.java) ?: nullValue
    }
}