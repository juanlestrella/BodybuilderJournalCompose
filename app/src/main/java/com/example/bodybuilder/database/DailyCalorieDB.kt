package com.example.bodybuilder.database

import androidx.room.*
import com.example.bodybuilder.Converters
import com.example.bodybuilder.data.DailyCalorieData.DailyCalorieData
import com.example.bodybuilder.models.DailyCalorieEntity

@Dao
interface DailyCalorieDao{
    @RewriteQueriesToDropUnusedColumns
    @Query("SELECT * FROM DailyCalorieEntity ORDER BY idDailyCalorie DESC")
    fun getAllDailyCalorie(): List<DailyCalorieData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDailyCalorie(dailyCalorie: DailyCalorieEntity)
}

@Database(entities = [DailyCalorieEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class DailyCalorieDB : RoomDatabase(){
    abstract val dailyCalorieDao: DailyCalorieDao
}