package com.example.bodybuilder.database

import android.app.Application
import android.content.Context
import androidx.room.*
import com.example.bodybuilder.entities.BmiData

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


private lateinit var INSTANCE: BmiDB

fun getBmiDB(context: Context) : BmiDB {
    synchronized(BmiDB::class.java){
        if(!::INSTANCE.isInitialized){
            INSTANCE = Room.databaseBuilder(
                context,
                BmiDB::class.java,
                "bmi_db"
            ).build()
        }
        return INSTANCE
    }
}