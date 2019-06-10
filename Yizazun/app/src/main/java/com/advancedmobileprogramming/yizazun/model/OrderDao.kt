package com.advancedmobileprogramming.yizazun.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface OrderDao {
    @Query("SELECT * from `order_list`")
    fun getAllOrder(): LiveData<List<Order>>

    @Query("SELECT * From `order_list` WHERE user_id=:userId")
    fun getOrderByUserId(userId:Long): LiveData<List<Order>>

    @Insert
    fun insertOrder(order: Order)

    @Delete
    fun deleteFood(id:Long)

    @Update
    fun updateFood(id:Long,order: Order)
}