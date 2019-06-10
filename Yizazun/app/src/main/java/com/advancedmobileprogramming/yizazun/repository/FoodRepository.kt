package com.advancedmobileprogramming.yizazun.repository

import androidx.lifecycle.LiveData
import com.advancedmobileprogramming.yizazun.model.Food
import com.advancedmobileprogramming.yizazun.model.FoodDao
import com.advancedmobileprogramming.yizazun.network.FoodYizazunApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response

class FoodRepository {
    private val foodDao: FoodDao? = null
    fun findFoodById(id: Long) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response: Response<LiveData<Food>> = FoodYizazunApiService.getInstance().findFoodByIdAsync(id).await()
                if (!response.isSuccessful) {
                    foodDao?.getFoodByFoodId(id)}
            }
            finally {
                println("successful")
            }

        }
    }

    fun findAllFood() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response: Response<LiveData<List<Food>>> = FoodYizazunApiService.getInstance().findAllFoodAsync().await()
                if (!response.isSuccessful) {
                    foodDao?.getAllFood()}
            }
            finally {
                println("successful")
            }

        }
    }

    fun insertFood(food: Food) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response: Response<Void> = FoodYizazunApiService.getInstance().insertFoodAsync(food).await()
                if (!response.isSuccessful) {
                    foodDao?.insertFood(food)
                }
            }
            finally {
                println("successful")
            }
        }
    }
    fun updateFood(id: Long,food: Food) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response: Response<Void> = FoodYizazunApiService.getInstance().updateFoodAsnc(id,food).await()
                if (!response.isSuccessful) {
                    foodDao?.updateFood(id,food)
                }
            }
            finally {
                println("successful")
            }
        }
    }
    fun deleteFood(id: Long) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response: Response<Void> = FoodYizazunApiService.getInstance().deleteFoodAsync(id).await()
                if (!response.isSuccessful) {
                    foodDao?.deleteFood(id)
                }
            }
            finally {
                println("successful")
            }
        }
    }
}