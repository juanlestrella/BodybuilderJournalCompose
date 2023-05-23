package com.example.bodybuilder.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.bodybuilder.Converters
import com.example.bodybuilder.models.ImagesEntity
import retrofit2.http.GET

@Dao
interface ImagesDao {

    @Query("SELECT * FROM ImagesEntity ORDER BY idImages")
    fun getAllImages() : List<ImagesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertImages(imagesEntity: ImagesEntity)
}

@Database(entities = [ImagesEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class ImagesDB : RoomDatabase(){
    abstract val imagesDao: ImagesDao
}