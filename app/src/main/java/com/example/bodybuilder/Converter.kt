package com.example.bodybuilder

import androidx.room.TypeConverter
import com.example.bodybuilder.data.BmiData.BmiData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {

    @TypeConverter
    fun stringToBmiData(value: String): BmiData? {
        val gson = Gson()
        val type = object : TypeToken<BmiData>() {}.type

        if (value.isNullOrEmpty()){
            return null
        }
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun BmiDataToString(value: BmiData): String {
        val gson = Gson()
        val type = object : TypeToken<BmiData>() {}.type
        return gson.toJson(value)
    }
}