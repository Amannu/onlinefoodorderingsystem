package com.advancedmobileprogramming.yizazun.repository

import androidx.lifecycle.LiveData
import com.advancedmobileprogramming.yizazun.model.Order
import com.advancedmobileprogramming.yizazun.model.OrderDao
import com.advancedmobileprogramming.yizazun.network.OrderYizazunApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response

class OrderRepository {
    private val orderDao: OrderDao? = null
    fun findOrderByUserId(id: Long) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response: Response<LiveData<Order>> = OrderYizazunApiService.getInstance().findOrderByUserIdAsync(id).await()
                if (!response.isSuccessful) {
                    orderDao?.getOrderByUserId(id)}
            }
            finally {
                println("successful")
            }

        }
    }

    fun findAllOrder() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response: Response<LiveData<List<Order>>> = OrderYizazunApiService.getInstance().findAllOrderAsync().await()
                if (!response.isSuccessful) {
                    orderDao?.getAllOrder()}
            }
            finally {
                println("successful")
            }

        }
    }

    fun insertFood(order: Order) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response: Response<Void> = OrderYizazunApiService.getInstance().insertOrderAsync(order).await()
                if (!response.isSuccessful) {
                    orderDao?.insertOrder(order)
                }
            }
            finally {
                println("successful")
            }
        }
    }
    fun updateFood(id: Long,order: Order) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response: Response<Void> = OrderYizazunApiService.getInstance().updateOrderAsnc(id,order).await()
                if (!response.isSuccessful) {
                    orderDao?.updateFood(id,order)
                }
            }
            finally {
                println("successful")
            }
        }
    }
    fun deleteFood(id: Long) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response: Response<Void> = OrderYizazunApiService.getInstance().deleteOrderAsync(id).await()
                if (!response.isSuccessful) {
                    orderDao?.deleteFood(id)
                }
            }
            finally {
                println("successful")
            }
        }
    }
}