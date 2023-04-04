package com.example.bodybuilder.di

import android.content.Context
import com.example.bodybuilder.BaseApplication
import com.example.bodybuilder.Constants
import com.example.bodybuilder.database.BmiDB
import com.example.bodybuilder.database.getBmiDB
import com.example.bodybuilder.network.ApiService
import com.example.bodybuilder.repository.Repository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
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
    fun provideMoshiBuilder() : Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun provideApi(moshi: Moshi) : ApiService {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(ApiService::class.java)
    }

//    @Singleton
//    @Provides
//    fun provideBmiDB(context: Context) : BmiDB {
//        return getBmiDB(context)
//    }

    @Singleton
    @Provides
    fun provideRepository(api : ApiService, ) : Repository {
        return Repository(api, ) // bmiDB bmiDB: BmiDB
    }

}