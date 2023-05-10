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
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import retrofit2.converter.moshi.MoshiConverterFactory
import java.lang.reflect.Type

class Converters {
    val gson = Gson()
    //val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @TypeConverter
    fun toMacrosDetail(macrosDetail: MacrosDetail?) : String? {
        val ans = gson.toJson(macrosDetail, MacrosDetail::class.java)
        Log.i("CONVERTER toMacrosDetail", ans)
        return ans
        //return macrosDetail?.let { it.toString() }
    }

    @TypeConverter
    fun fromMacrosDetail(value : String) : MacrosDetail {
        Log.i("CONVERTER fromMacrosDetail", value ?: "")
        //return moshi.adapter(MacrosDetail::class.java).fromJson(value)
        //return Json.decodeFromString<MacrosDetail>(value as String)
        return gson.fromJson(value, MacrosDetail::class.java)
        //return MacrosDetail(0.toFloat(),0.toFloat(),0.toFloat())
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