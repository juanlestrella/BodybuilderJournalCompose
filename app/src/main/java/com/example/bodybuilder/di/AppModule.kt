package com.example.bodybuilder.di

import android.content.Context
import androidx.room.Room
import com.example.bodybuilder.BaseApplication
import com.example.bodybuilder.Constants
import com.example.bodybuilder.database.BmiDB
import com.example.bodybuilder.database.BmiDao
import com.example.bodybuilder.network.ApiService
import com.example.bodybuilder.repository.Repository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // how long the dependencies will live (Singleton is like an application lifetime)
object AppModule {

    @Singleton // theres only a single instants
    @Provides
    fun provideApplication(@ApplicationContext app : Context) : BaseApplication {
        return app as BaseApplication
    }

    @Singleton
    @Provides
    fun provideMoshiBuilder() : Moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()


    @Singleton
    @Provides
    fun provideOkHttp() : OkHttpClient =
        OkHttpClient
            .Builder()
            .build()

    @Singleton
    @Provides
    fun provideApi(moshi: Moshi, okHttpClient: OkHttpClient) : ApiService {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideBmiDB(@ApplicationContext appContext: Context) =
        Room.databaseBuilder(
            appContext,
            BmiDB::class.java,
            "bmi_db"
        ).build()


    @Singleton
    @Provides
    fun provideBmiDao(bmiDB: BmiDB) = bmiDB.bmiDao

    @Singleton
    @Provides
    fun provideRepository(api : ApiService, bmiDao: BmiDao) : Repository {
        return Repository(api, bmiDao) // bmiDB bmiDB: BmiDB
    }

}