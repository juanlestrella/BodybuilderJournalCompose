package com.example.bodybuilder.repository

import android.util.Log
import com.example.bodybuilder.Constants
import com.example.bodybuilder.database.BmiDao
import com.example.bodybuilder.data.BmiData.BmiData
import com.example.bodybuilder.data.BodyFatData.BodyFatData
import com.example.bodybuilder.data.DailyCalorieData.DailyCalorieData
import com.example.bodybuilder.data.DailyCalorieData.DailyCalorieGoalsData
import com.example.bodybuilder.data.DailyCalorieData.GainWeightData
import com.example.bodybuilder.data.DailyCalorieData.LossWeightData
import com.example.bodybuilder.data.MacrosAmountData.MacrosAmountData
import com.example.bodybuilder.data.MacrosAmountData.MacrosData
import com.example.bodybuilder.response.BmiResponse
import com.example.bodybuilder.network.ApiService
import com.example.bodybuilder.response.BodyFatResponse
import com.example.bodybuilder.response.DailyCalorieResponse
import com.example.bodybuilder.response.MacrosAmountsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(
    private val api: ApiService,
    private val bmiDao: BmiDao,
) {

    private val tag = Repository::class.simpleName

    private val _bmi = MutableStateFlow(BmiData(0.toFloat(), "", ""))
    val bmi: StateFlow<BmiData> = _bmi.asStateFlow()

    private val _bodyFat = MutableStateFlow(BodyFatData(0.toFloat(), "", 0.toFloat(), 0.toFloat(),0.toFloat()))
    val bodyFat: StateFlow<BodyFatData> = _bodyFat.asStateFlow()

    private val _dailyCalorie =
        MutableStateFlow(
            DailyCalorieData(
                0,
                DailyCalorieGoalsData(
                    0,
                    LossWeightData("",0),
                    LossWeightData("",0),
                    LossWeightData("",0),
                    GainWeightData("", 0),
                    GainWeightData("", 0),
                    GainWeightData("", 0),
                )
            )
        )
    val dailyCalorie : StateFlow<DailyCalorieData> = _dailyCalorie.asStateFlow()

    private val _macroCalculator = MutableStateFlow(
        MacrosAmountData(
            0.toFloat(),
            MacrosData(0.toFloat(),0.toFloat(),0.toFloat()), // balanced
            MacrosData(0.toFloat(),0.toFloat(),0.toFloat()), // low fat
            MacrosData(0.toFloat(),0.toFloat(),0.toFloat()), // low carbs
            MacrosData(0.toFloat(),0.toFloat(),0.toFloat()), // high protein
        )
    )
    val macroCalculator : StateFlow<MacrosAmountData> = _macroCalculator.asStateFlow()


    suspend fun getBmiFromApi(age: Int, weight: Float, height: Float){
        withContext(Dispatchers.IO){
            val response : Response<BmiResponse> = api.getResponseBmi(Constants.KEY, Constants.HOST, age, weight, height)
            if (response.isSuccessful){
                _bmi.value = response.body()!!.data
            }else {
                response.errorBody()?.string()?.let { Log.e(tag, "$it(BMI ERROR)") }
            }
        }
    }

    suspend fun getBodyFatFromApi(
        age: Int,
        gender: String,
        weight: Number,
        height: Number,
        neck: Number,
        waist: Number,
        hip: Number
    ){
        withContext(Dispatchers.IO){
            val response : Response<BodyFatResponse> =
                api.getResponseBodyFat(
                    Constants.KEY,
                    Constants.HOST,
                    age,
                    gender,
                    weight,
                    height,
                    neck,
                    waist,
                    hip
                )
            if (response.isSuccessful) {
                _bodyFat.value = response.body()!!.data
            } else {
                response.errorBody()?.string()?.let { Log.e(tag, "$it(Body Fat Error)") }
            }
        }
    }

    suspend fun getDailyCalorieFromApi(
        age: Int,
        gender: String,
        height: Number,
        weight: Number,
        activityLevel: String
    ){
        withContext(Dispatchers.IO){
            val response: Response<DailyCalorieResponse> =
                api.getResponseDailyCalorie(
                    Constants.KEY,
                    Constants.HOST,
                    age,
                    gender,
                    height,
                    weight,
                    activityLevel
                )
            if (response.isSuccessful){
                _dailyCalorie.value = response.body()!!.data
            } else {
                response.errorBody()?.string()?.let { Log.e(tag, "$it (Daily Calorie Error)") }
            }
        }
    }

    suspend fun getMacrosCalculatorFromApi(
        age: Int,
        gender: String,
        height: Number,
        weight: Number,
        activityLevel: Number,
        goal: String
    ){
        withContext(Dispatchers.IO){
            val response: Response<MacrosAmountsResponse> =
                api.getResponseMacroCalculator(
                    Constants.KEY,
                    Constants.HOST,
                    age,
                    gender,
                    height,
                    weight,
                    activityLevel,
                    goal
                )
            if(response.isSuccessful){
                _macroCalculator.value = response.body()!!.data
            } else {
                response.errorBody()?.let { Log.e(tag, "$it (Macros Calculator Error") }
            }
        }
    }
}

//    suspend fun insertBmiToDatabase(){
//        // if (_bmiResponse.value) // check if stateflow has actual value
//        bmiDao.insertBmi(_bmi.value)
//    }

//    suspend fun getBmiFromDatabase() : BmiData {
//        Log.i("REPO", bmiDao.getBmi().toString())
//        return bmiDao.getBmi()
//    }