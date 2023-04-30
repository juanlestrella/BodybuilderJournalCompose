package com.example.bodybuilder.database

import androidx.room.*
//import com.example.bodybuilder.Converter
import com.example.bodybuilder.data.BmiData.BmiData
import com.example.bodybuilder.entities.BmiEntity
import retrofit2.Converter

@Dao
interface BmiDao {

    @Query("SELECT bmi FROM BmiEntity")
    fun getAllBmi(): List<BmiData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBmi(bmi: BmiData)
}

@Database(entities = [BmiEntity::class], version = 1)
@TypeConverters(Converter::class)
abstract class BmiDB : RoomDatabase(){
    abstract val bmiDao: BmiDao
}