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
import com.example.bodybuilder.data.MacrosData.MacrosData
import com.example.bodybuilder.data.MacrosData.MacrosDetail
import com.example.bodybuilder.database.BodyFatDao
import com.example.bodybuilder.database.DailyCalorieDao
import com.example.bodybuilder.database.MacrosDao
import com.example.bodybuilder.models.BmiEntity
import com.example.bodybuilder.models.BodyFatEntity
import com.example.bodybuilder.models.DailyCalorieEntity
import com.example.bodybuilder.models.MacrosEntity
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
    private val bodyFatDao: BodyFatDao,
    private val dailyCalorieDao: DailyCalorieDao,
    private val macrosDao: MacrosDao
) {

    private val tag = Repository::class.simpleName

    /***** STATES *****/
    private val _bmi = MutableStateFlow(BmiData(0.toFloat(), "", ""))
    val bmi: StateFlow<BmiData> = _bmi.asStateFlow()

    private val _bmiList: MutableStateFlow<List<BmiData>> = MutableStateFlow(listOf())
    val bmiList: StateFlow<List<BmiData>> = _bmiList

    private val _bodyFat = MutableStateFlow(BodyFatData(0.toFloat(), "", 0.toFloat(), 0.toFloat(),0.toFloat()))
    val bodyFat: StateFlow<BodyFatData> = _bodyFat.asStateFlow()
    
    private val _bodyFatList : MutableStateFlow<List<BodyFatData>> = MutableStateFlow(listOf())
    val bodyFatList: StateFlow<List<BodyFatData>> = _bodyFatList

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

    private val _dailyCalorieList : MutableStateFlow<List<DailyCalorieData>> = MutableStateFlow(listOf())
    val dailyCalorieList: StateFlow<List<DailyCalorieData>> = _dailyCalorieList

    private val _macros = MutableStateFlow(
        MacrosData(
            0.toFloat(),
            MacrosDetail(0.toFloat(),0.toFloat(),0.toFloat()), // balanced
            MacrosDetail(0.toFloat(),0.toFloat(),0.toFloat()), // low fat
            MacrosDetail(0.toFloat(),0.toFloat(),0.toFloat()), // low carbs
            MacrosDetail(0.toFloat(),0.toFloat(),0.toFloat()), // high protein
        )
    )
    val macros : StateFlow<MacrosData> = _macros.asStateFlow()

    private val _macrosList : MutableStateFlow<List<MacrosData>> = MutableStateFlow(listOf())
    val macrosList: StateFlow<List<MacrosData>> = _macrosList

    /***** API FUNCTIONS *****/
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
                _macros.value = response.body()!!.data
            } else {
                response.errorBody()?.let { Log.e(tag, "$it (Macros Calculator Error") }
            }
        }
    }
    /***** LOCAL DATABASE FUNCTIONS *****/
    /***BMI***/
    suspend fun insertBmiToDB(data: BmiData) = withContext(Dispatchers.IO) {
        bmiDao.insertBmi(BmiEntity(data.bmi, data.health, data.healthy_bmi_range))
        getAllBmiFromDB()
    }

    /**
     * Necessary because its also called in the VM's init
     */
    suspend fun getAllBmiFromDB() = withContext(Dispatchers.IO){
        _bmiList.value = bmiDao.getAllBmi()
    }

    /***BODY FAT***/
    suspend fun insertBodyFatToDB(data: BodyFatData) = withContext(Dispatchers.IO){
        bodyFatDao.insertBodyFat(
            BodyFatEntity(
                data.bodyFat,
                data.category,
                data.bodyFatMass,
                data.leanBodyMass,
                data.bmi
            )
        )
        getAllBodyFatFromDB()
    }

    suspend fun getAllBodyFatFromDB() = withContext(Dispatchers.IO){
        _bodyFatList.value = bodyFatDao.getAllBodyFat()
    }

    /***DAILY CALORIE***/
    suspend fun insertDailyCalorieToDB(data: DailyCalorieData) = withContext(Dispatchers.IO){
        dailyCalorieDao.insertDailyCalorie(
            DailyCalorieEntity(
                data.BMR,
                data.goals
            )
        )
        getAllDailyCalorieFromDB()
    }

    suspend fun getAllDailyCalorieFromDB() = withContext(Dispatchers.IO){
        _dailyCalorieList.value = dailyCalorieDao.getAllDailyCalorie()
    }

    /***MACROS***/
    suspend fun insertMacrosToDB(data: MacrosData) = withContext(Dispatchers.IO){
        macrosDao.insertMacros(
            MacrosEntity(
                data.calorie,
                data.balanced,
                data.lowFat,
                data.lowCarbs,
                data.highProtein
            )
        )
        getAllMacrosFromDB()
    }
    suspend fun getAllMacrosFromDB() = withContext(Dispatchers.IO){
        _macrosList.value = macrosDao.getAllMacros()
    }
}