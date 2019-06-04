package com.advancedmobileprogramming.yizazun.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface OrderDao {
    @Query("SELECT * from `order` ORDER BY order_id")
    fun getAllOrder(): LiveData<List<Order>>

    @Query("SELECT * From `order` WHERE first_name=:firstName and last_name=:lastName")
    fun getFoodByFoodName(firstName:String,lastName:String): LiveData<List<Order>>

    @Insert
    fun insertOrder(order: Order)

    @Delete
    fun deleteFood(order: Order)

    @Update
    fun updateFood(order: Order)
}