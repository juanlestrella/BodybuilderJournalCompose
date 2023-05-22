package com.example.bodybuilder.database

import androidx.room.*
import com.example.bodybuilder.models.ImagesEntity

@Dao
interface ImagesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertImages(imagesEntity: ImagesEntity)
}

@Database(entities = [ImagesEntity::class], version = 1)
abstract class ImagesDB : RoomDatabase(){
    abstract val imagesDao: ImagesDao
}