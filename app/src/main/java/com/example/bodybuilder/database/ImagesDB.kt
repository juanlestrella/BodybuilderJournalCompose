package com.example.bodybuilder.database

import androidx.room.*
import com.example.bodybuilder.models.ImagesEntity
import retrofit2.http.GET

@Dao
interface ImagesDao {

    @GET("SELECT imagesString FROM ImagesEntity ORDER BY idImages")
    fun getAllImages() : List<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertImages(imagesEntity: ImagesEntity)
}

@Database(entities = [ImagesEntity::class], version = 1)
abstract class ImagesDB : RoomDatabase(){
    abstract val imagesDao: ImagesDao
}