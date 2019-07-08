package com.advancedmobileprogramming.yizazun.repository

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.lifecycle.LiveData
import com.advancedmobileprogramming.yizazun.model.dao.OrderListDao
import com.advancedmobileprogramming.yizazun.model.entities.OrderList
import com.advancedmobileprogramming.yizazun.model.network.OrderListApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response

class OrderListRepository(private val orderListDao: OrderListDao, private val orderListApiService: OrderListApiService, private val application: Application) {
    suspend fun getOrderListById(id:Long): LiveData<OrderList> = orderListDao.getOrderById(id)

    suspend fun getAllOrderLists(): LiveData<List<OrderList>>{

        val orderList = orderListDao.getAllOrder().value
       // if(orderList == null || orderList.isEmpty()){
            refreshAllOrders()
      //  }
        return orderListDao.getAllOrder()
    }

    suspend fun insertOrderList(orderList: OrderList) {
        val connectivityManager=  application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo:NetworkInfo=connectivityManager.activeNetworkInfo


        if(networkInfo?.isConnected){
            orderListApiService.postOrderList(orderList)
            orderListDao.insertOrder(orderList)
        }

    }

    suspend fun deleteOrderList(orderList: OrderList){
        val connectivityManager=  application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo:NetworkInfo=connectivityManager.activeNetworkInfo


        if(networkInfo?.isConnected){
            orderListApiService.deleteOrderList(orderList.id)
            orderListDao.deleteOrderById(orderList)
        }
    }

    suspend fun updateOrderList(orderList: OrderList){
        val connectivityManager=  application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo:NetworkInfo=connectivityManager.activeNetworkInfo


        if(networkInfo?.isConnected){
            orderListApiService.updateOrderList(orderList.id,orderList)
            orderListDao.updateOrder(orderList)
        }
    }
    suspend fun refreshAllOrders(){
        val connectivityManager=  application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo =connectivityManager.activeNetworkInfo


        if(networkInfo?.isConnected){
            try {
                GlobalScope.launch(Dispatchers.IO) {
                    val response: Response<List<OrderList>> = orderListApiService.getOrderList().await()
                    val orderLists:List<OrderList>? = response.body()
                    if(orderLists!=null){
                        for (orderList in orderLists){
                            orderListDao.insertOrder(orderList)
                        }
                    }
                }
            }catch (e: Exception){

            }
        }

    }
}