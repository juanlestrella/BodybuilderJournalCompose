package com.example.bodybuilder.database

import androidx.room.*
import com.example.bodybuilder.Converters
import com.example.bodybuilder.data.MacrosData.MacrosData
import com.example.bodybuilder.models.MacrosEntity

@Dao
interface MacrosDao{
    @RewriteQueriesToDropUnusedColumns
    @Query("SELECT * FROM MacrosEntity ORDER BY idMacros")
    fun getAllMacros(): List<MacrosData>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insertMacros(macros: MacrosEntity)
}

@Database(entities = [MacrosEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class MacrosDB : RoomDatabase(){
    abstract val macrosDao: MacrosDao
}