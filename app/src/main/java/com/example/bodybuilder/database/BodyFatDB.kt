package com.example.bodybuilder.database

import androidx.room.*
import com.example.bodybuilder.data.BodyFatData.BodyFatData
import com.example.bodybuilder.models.BodyFatEntity

@Dao
interface BodyFatDao {
    @Query("SELECT * FROM BodyFatEntity ORDER BY idBodyFat DESC")
    fun getAllBodyFat(): List<BodyFatData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBodyFat(bodyFat : BodyFatEntity)
}

@Database(entities = [BodyFatEntity::class], version = 1)
abstract class BodyFatDB : RoomDatabase() {
    abstract val bodyFatDao : BodyFatDao
}