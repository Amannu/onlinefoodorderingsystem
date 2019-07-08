package com.advancedmobileprogramming.yizazun.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.advancedmobileprogramming.yizazun.model.entities.OrderList

@Dao
interface OrderListDao {
    @Query("SELECT * FROM order_list WHERE  id = :id")
    fun getOrderById(id: Long): LiveData<OrderList>

    @Query("SELECT * FROM order_list ORDER BY  id ")
    fun getAllOrder(): LiveData<List<OrderList>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrder(order: OrderList)

    @Update
    fun updateOrder(order: OrderList)

    @Delete
    fun deleteOrderById(order: OrderList)
}