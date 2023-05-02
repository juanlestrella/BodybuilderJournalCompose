package com.example.bodybuilder.database

import androidx.room.*
import com.example.bodybuilder.data.BmiData.BmiData
import com.example.bodybuilder.models.BmiEntity

@Dao
interface BmiDao {

    @Query("SELECT * FROM BmiEntity")
    fun getAllBmi(): List<BmiData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBmi(bmi: BmiEntity)
}

@Database(entities = [BmiEntity::class], version = 1)
//@TypeConverters(Converters::class)
abstract class BmiDB : RoomDatabase(){
    abstract val bmiDao: BmiDao
}