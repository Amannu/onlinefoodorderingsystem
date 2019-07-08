package com.advancedmobileprogramming.yizazun.model.network

import com.advancedmobileprogramming.yizazun.model.entities.OrderList
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

interface OrderListApiService {
    @GET("orderLists/{id}")
    fun getOrderListById(@Path("id") id:Long): Deferred<Response<OrderList>>

    @GET("orderLists/")
    fun getOrderList(): Deferred<Response<List<OrderList>>>


    @POST("orderLists")
    fun postOrderList(newOrderList: OrderList): Deferred<Response<Void>>

    @PUT("orderLists/{id}")
    fun updateOrderList(@Path("id") id: Long, @Body orderList: OrderList): Deferred<Response<OrderList>>

    @DELETE("orderLists/{id}")
    fun deleteOrderList(@Path("id") id:Long): Deferred<Response<Void>>

    companion object {

        private val baseUrl = "http://localhost:8080/"

        fun getInstance(): OrderListApiService {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient
                .Builder()
                .addInterceptor(interceptor)
                .build()

            val retrofit: Retrofit =  Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()

            return retrofit.create(OrderListApiService::class.java)
        }
    }
}