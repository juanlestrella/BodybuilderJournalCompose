package com.example.bodybuilder.network

import com.example.bodybuilder.entities.BmiData
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {
    @GET("metric")
    suspend fun getMetric(
        @Header("X-RapidAPI-Host") rapidHost: String,
        @Header("X-RapidAPI-Key") rapidKey: String,
        @Query("weight") weight: String,
        @Query("height") height: String
    ) : BmiData
    @GET("imperial")
    suspend fun getImperial(
        @Header("X-RapidAPI-Host") rapidHost: String,
        @Header("X-RapidAPI-Key") rapidKey: String,
        @Query("weight") weight: String,
        @Query("height") height: String
    ) : BmiData

    @GET("weight-category")
    suspend fun getCategory(
        @Header("X-RapidAPI-Host") rapidHost: String,
        @Header("X-RapidAPI-Key") rapidKey: String,
        @Query("bmi") bmi: Int
    )
}