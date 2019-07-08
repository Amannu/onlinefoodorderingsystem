package com.advancedmobileprogramming.yizazun.repository

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.lifecycle.LiveData
import com.advancedmobileprogramming.yizazun.model.dao.FoodDao
import com.advancedmobileprogramming.yizazun.model.entities.Food
import com.advancedmobileprogramming.yizazun.model.network.FoodApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response

class FoodRepository (private val foodDao: FoodDao, private val foodApiService: FoodApiService, private val application: Application) {
    suspend fun getFoodById(id:Long): LiveData<Food> = foodDao.getFoodById(id)

    suspend fun getAllFood(): LiveData<List<Food>> {

        val food = foodDao.getAllFood().value
        if(food == null || food.isEmpty()){
            refreshAllFoods()
        }
        return foodDao.getAllFood()
    }

    suspend fun insertFood(food: Food) {
        val connectivityManager=  application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo:NetworkInfo=connectivityManager.activeNetworkInfo


        if(networkInfo?.isConnected){
            foodApiService.postFoods(food)
            foodDao.insertFood(food)
        }

    }

    suspend fun deleteFood(food: Food){
        val connectivityManager=  application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo:NetworkInfo=connectivityManager.activeNetworkInfo


        if(networkInfo?.isConnected){
            foodApiService.deleteFoods(food.id)
            foodDao.deleteFoodById(food)
        }
    }

    suspend fun updateFood(food: Food){
        val connectivityManager=  application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo:NetworkInfo=connectivityManager.activeNetworkInfo


        if(networkInfo?.isConnected){
            foodApiService.updateFoods(food.id,food)
            foodDao.updateFood(food)
        }
    }
    fun refreshAllFoods(){
        val connectivityManager=  application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo =connectivityManager.activeNetworkInfo


        if(networkInfo?.isConnected){
            try {
                GlobalScope.launch(Dispatchers.IO) {
                    val response: Response<List<Food>> = foodApiService.getFoods().await()
                    val foods:List<Food>? = response.body()
                    if(foods!=null){
                        for (food in foods){
                            foodDao.insertFood(food)
                        }
                    }
                }
            }catch (e: Exception){

            }
        }

    }
}