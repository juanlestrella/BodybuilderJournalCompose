package com.example.bodybuilder

import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
import java.io.ByteArrayOutputStream

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
    fun fromList(value: List<String>) : String = Json.encodeToString(value)

    @TypeConverter
    fun toList(value: String) : List<String> =
        try{
            Json.decodeFromString(value) ?: listOf()
        } catch(e: Exception){
            listOf<String>()
        }
}

//@TypeConverter
//fun fromBitmap(listOfBitmap: List<Bitmap>?): List<ByteArray>? {
//    return if (listOfBitmap == null){
//        listOf()
//    }else{
//        val lstByteArray = mutableListOf<ByteArray>()
//        listOfBitmap?.forEach { bitmap ->
//            val outputStream = ByteArrayOutputStream()
//            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
//            lstByteArray.add(outputStream.toByteArray())
//        }
//        lstByteArray
//    }
//
//}
//
//@TypeConverter
//fun toBitmap(byteArray: List<ByteArray>?): List<Bitmap>? {
//    return if(byteArray == null){
//        listOf()
//    }else {
//        val lstBitmap = mutableListOf<Bitmap>()
//        byteArray?.forEach {  byteArray ->
//            lstBitmap.add(BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size))
//        }
//        lstBitmap
//    }
//}