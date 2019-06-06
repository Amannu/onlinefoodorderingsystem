package com.advancedmobileprogramming.yizazun.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface OrderDao {
    @Query("SELECT * from `order_list`")
    fun getAllOrder(): LiveData<List<Order>>

    @Query("SELECT * From `order_list` WHERE user_id=:userId")
    fun getFoodByUserId(userId:Long): LiveData<List<Order>>

    @Insert
    fun insertOrder(order: Order)

    @Delete
    fun deleteFood(order: Order)

    @Update
    fun updateFood(order: Order)
}