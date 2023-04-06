package com.example.bodybuilder.repository

import android.util.Log
import com.example.bodybuilder.Constants
import com.example.bodybuilder.database.BmiDB
import com.example.bodybuilder.database.BmiDao
import com.example.bodybuilder.entities.BmiData
import com.example.bodybuilder.entities.BmiResponse
import com.example.bodybuilder.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(
    private val api: ApiService,
    private val bmiDao: BmiDao,
) {
//    private val _bmiState = MutableStateFlow<BmiData>(BmiData())
//    val bmiState: StateFlow<BmiData> = _bmiState.asStateFlow()
    suspend fun getBMI(age: Int, weight: Float, height: Float){
        withContext(Dispatchers.IO){
            // Network
            val response : Response<BmiResponse> = api.getBmi(Constants.KEY, Constants.HOST, age, weight, height)
            if (response.isSuccessful){
                // need to find a way to get the proper response
                // Room Database
                bmiDao.insertBmi(response.body()!!.data)
                Log.i("REPO", bmiDao.getBmi().toString())
                //_bmiState.value = bmiDao.getBmi()
            }else {
                response.errorBody()?.string()?.let { Log.e("REPOSITORY ERR", it.toString()) }
            }
        }
    }
}