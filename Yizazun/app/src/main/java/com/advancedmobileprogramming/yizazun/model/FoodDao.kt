package com.advancedmobileprogramming.yizazun.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.*

@Dao
interface FoodDao {

    @Query("SELECT * from food")
    fun getAllFood(): LiveData<List<Food>>

    @Query("SELECT * From food WHERE id=:foodId")
    fun getFoodByFoodName(foodId:String):LiveData<Food>

    @Insert
    fun insertFood(food: Food)

    @Delete
    fun deleteFood(food: Food)

    @Update
    fun updateFood(food: Food)
}