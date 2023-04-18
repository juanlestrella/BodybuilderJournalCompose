package com.example.bodybuilder.database

import androidx.room.*
import com.example.bodybuilder.data.BmiData.BmiData

@Dao
interface BmiDao {

    @Query("SELECT * FROM BmiData")
    fun getBmi(): BmiData

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBmi(bmiResult: BmiData)
}

@Database(entities = [BmiData::class], version = 1)
abstract class BmiDB : RoomDatabase(){
    abstract val bmiDao: BmiDao
}