package com.advancedmobileprogramming.yizazun.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.*

@Dao
interface FoodDao {

    @Query("SELECT * from food ORDER BY food_name")
    fun getAllFood(): LiveData<List<Food>>

    @Query("SELECT * From food WHERE food_name=:foodName")
    fun getFoodByFoodName(foodName:String):LiveData<Food>

    @Insert
    fun insertFood(food: Food)

    @Delete
    fun deleteFood(food: Food)

    @Update
    fun updateFood(food: Food)
}