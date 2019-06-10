package com.advancedmobileprogramming.yizazun.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.*

@Dao
interface FoodDao {

    @Query("SELECT * from food")
    fun getAllFood(): LiveData<List<Food>>

    @Query("SELECT * From food WHERE id=:foodId")
    fun getFoodByFoodId(foodId:Long):LiveData<Food>

    @Insert
    fun insertFood(food: Food)

    @Delete
    fun deleteFood(id:Long)

    @Update
    fun updateFood(id:Long,food: Food)
}