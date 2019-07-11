package com.example.yizazun.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.yizazun.data.entity.Food

@Dao
interface FoodDao {
    @Query("SELECT * FROM food WHERE id = :id")
    fun getFoodById(id:Int): LiveData<Food>

    @Query("SELECT * FROM food")
    fun getFoods(): LiveData<List<Food>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFoods(food: Food)

    @Update
    fun updateFoods(food: Food)

    @Delete
    fun deleteFoods(food: Food)
}