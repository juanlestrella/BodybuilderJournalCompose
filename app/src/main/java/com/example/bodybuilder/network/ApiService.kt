package com.example.bodybuilder.network

import com.example.bodybuilder.response.BmiResponse
import com.example.bodybuilder.response.BodyFatResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {

    @GET("bmi")
    suspend fun getResponseBmi(
        @Header("X-RapidAPI-Key") rapidKey: String,
        @Header("X-RapidAPI-Host") rapidHost: String,
        @Query("age") age: Number,
        @Query("weight") weight: Number,
        @Query("height") height: Number
    ) : Response<BmiResponse>

    @GET("bodyfat")
    suspend fun getResponseBodyFat(
        @Header("X-RapidAPI-Key") rapidKey: String,
        @Header("X-RapidAPI-Host") rapidHost: String,
        @Query("age") age: Number,
        @Query("gender") gender: String,
        @Query("weight") weight: Number,
        @Query("height") height: Number,
        @Query("neck") neck: Number,
        @Query("waist") waist: Number,
        @Query("hip") hip: Number
    ) : Response<BodyFatResponse>

    @GET("dailycalorie")
    suspend fun getResponseDailyCalorie(
        @Header("X-RapidAPI-Key") rapidKey: String,
        @Header("X-RapidAPI-Host") rapidHost: String,
        @Query("age") age: Number,
        @Query("gender") gender: String,
        @Query("height") height: Number,
        @Query("weight") weight: Number,
        @Query("activitylevel") activityLevel: String
    ) : Response<Unit>

    @GET("macrocalculator")
    suspend fun getResponseMacroCalculator(
        @Header("X-RapidAPI-Key") rapidKey: String,
        @Header("X-RapidAPI-Host") rapidHost: String,
        @Query("age") age: Number,
        @Query("gender") gender: String,
        @Query("height") height: Number,
        @Query("weight") weight: Number,
        @Query("activitylevel") activityLevel: Number,
        @Query("goal") goal: String //
    ) : Response<Unit>

    /**
     * TODO: Api calls
     * 1) BMI - Done
     * 2) Body Fat Percentage - Done
     * 3) Daily Calorie Required
     * 4) Macros
     */

    /**
     * Description
    Find the amount of macro nutrients in four different categories which are
    balanced, low-fat, low-carbs and high-protein for a specific calorie burned.
    There are 6 inputs which are age, gender, height, weight, activity level and goal.

    For activity level :

    "1" : "BMR",
    "2" : "Sedentary: little or no exercise",
    "3" : "Exercise 1-3 times/week",
    "4" : "Exercise 4-5 times/week",
    "5" : "Daily exercise or intense exercise 3-4 times/week",
    "6" : "Intense exercise 6-7 times/week",
    "7" : "Very intense exercise daily, or physical job"
    For goals :

    "maintain" : "maintain weight",
    "mildlose" : "Mild weight loss",
    "weightlose" : "Weight loss",
    "extremelose" : "Extreme weight loss",
    "mildgain" : "Mild weight gain",
    "weightgain" : "Weight gain",
    "extremegain" : "Extreme weight gain"
     */
}