package com.advancedmobileprogramming.yizazun.network

import androidx.lifecycle.LiveData
import com.advancedmobileprogramming.yizazun.model.Order
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

interface OrderYizazunApiService {
    @GET("orderLists/{id}")
    fun findFoodByIdAsync(@Path("id") id: Long): Deferred<Response<LiveData<Order>>>
    @GET("orderLists")
    fun findAllFoodAsync(): Deferred<Response<LiveData<List<Order>>>>
    @GET("orderLists/search/findByUserId?{id}")
    fun findFoodByUserIdAsync(@Path("id") id: Long): Deferred<Response<LiveData<Order>>>
    @POST("orderLists")
    fun insertOrderAsync(@Body newOrder: Order): Deferred<Response<Void>>
    @PUT("orderLists/{id}")
    fun updateOrderAsnc(@Path("id") id: Long, @Body newFood: Order): Deferred<Response<Void>>
    @DELETE("orderLists/{id}")
    fun deleteOrderAsync(@Path("id") id: Long): Deferred<Response<Void>>

    companion object {

        private val baseUrl = "http://localhost:8080/"

        fun getInstance(): OrderYizazunApiService {

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

            return retrofit.create(OrderYizazunApiService::class.java)
        }
    }
}