package com.example.bodybuilder.network

import com.example.bodybuilder.Constants
import com.example.bodybuilder.entities.BMIResult
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(Constants.BASE_URL)
    .build()

interface ApiService {
    @GET("metric")
    suspend fun getMetric(
        @Header("X-RapidAPI-Host") rapidHost: String,
        @Header("X-RapidAPI-Key") rapidKey: String,
        @Query("weight") weight: Float,
        @Query("height") height: Float
    ) : BMIResult
    @GET("imperial")
    suspend fun getImperial(
        @Header("X-RapidAPI-Host") rapidHost: String,
        @Header("X-RapidAPI-Key") rapidKey: String,
        @Query("weight") weight: Float,
        @Query("height") height: Float
    ) : BMIResult

    @GET("weight-category")
    suspend fun getCategory(
        @Header("X-RapidAPI-Host") rapidHost: String,
        @Header("X-RapidAPI-Key") rapidKey: String,
        @Query("bmi") bmi: Int
    )
}

object BodybuilderApi {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}