package com.example.bodybuilder.network

import com.example.bodybuilder.entities.BmiData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {
    @GET("bmi")
    suspend fun getBmi(
        @Header("X-RapidAPI-Host") rapidHost: String,
        @Header("X-RapidAPI-Key") rapidKey: String,
        @Query("age") age: Number,
        @Query("weight") weight: Number,
        @Query("height") height: Number
    ) : Response<BmiData>
}