package com.example.bodybuilder

import androidx.room.TypeConverter
import com.example.bodybuilder.data.BmiData.BmiData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class Converters {

    @TypeConverter
    fun toBmiData(value: String): List<BmiData> {
        val gson = Gson()
        if (value == null) {
            return emptyList()
        }
        val listType: Type = object : TypeToken<List<BmiData>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromBmiData(bmiData: List<BmiData>): String {
        val gson = Gson()
        return gson.toJson(bmiData)
    }

}