package com.advancedmobileprogramming.yizazun.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.advancedmobileprogramming.yizazun.model.entities.Food

@Dao
interface FoodDao {
    @Query("SELECT * FROM food WHERE  id = :id")
    fun getFoodById(id: Long): LiveData<Food>

    @Query("SELECT * FROM food ORDER BY  id ")
    fun getAllFood(): LiveData<List<Food>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFood(food: Food)

    @Update
    fun updateFood(food: Food)

    @Delete
    fun deleteFoodById(food: Food)
}