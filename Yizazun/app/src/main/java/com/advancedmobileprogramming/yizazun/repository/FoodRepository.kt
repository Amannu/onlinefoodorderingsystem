package com.example.yizazun.repository

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.example.yizazun.data.dao.FoodDao
import com.example.yizazun.data.entity.Food
import com.example.yizazun.services.FoodApiServices
import com.example.yizazun.utility.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception

class FoodRepository (private  val foodDao: FoodDao,private  val foodApiServices: FoodApiServices,private  val application: Application){
    fun getFoodById(id:Int): LiveData<Food> {
        return foodDao.getFoodById(id)
    }
    fun getAllFood(): LiveData<List<Food>> {

        //Check if we are opining the app for first time and the database is null or empty
        //This part is done only once as you told me in the office

        val food = foodDao.getFoods().value
        if(food == null || food.isEmpty()){
            refreshAllFood()
        }
        return foodDao.getFoods()
    }

    fun insertFood(food: Food){
        foodApiServices.insertFoodsAsync(food)
    }

    fun updateFood(food: Food){
        foodApiServices.updateFoodsAsync(food.id,food)
        foodDao.updateFoods(food)
    }

    fun deleteFood(food: Food){
        if(Constant.connected(application)){
            foodApiServices.deleteFoodsByIdAsync(food.id)
            foodDao.deleteFoods(food)
        }else{
            GlobalScope.launch(Dispatchers.Main) {
                Toast.makeText(application,"Your are not connected", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun refreshAllFood(){
        if(Constant.connected(application)){
            try {
                GlobalScope.launch(Dispatchers.IO) {
                    val response: Response<List<Food>> = foodApiServices.findAllFoodsAsync().await()
                    val foods:List<Food>? = response.body()
                    if(foods!=null){
                        for (f in foods){
                            foodDao.insertFoods(f)
                        }
                    }
                }
            }catch (e: Exception){

            }
        }

    }
}