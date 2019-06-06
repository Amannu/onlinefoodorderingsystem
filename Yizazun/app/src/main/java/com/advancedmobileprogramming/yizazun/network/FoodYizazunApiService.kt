package com.advancedmobileprogramming.yizazun.network

import androidx.lifecycle.LiveData
import com.advancedmobileprogramming.yizazun.model.Food
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

interface FoodYizazunApiService {

    @GET("foods/{id}")
    fun findFoodByIdAsync(@Path("id") id: Long): Deferred<Response<LiveData<Food>>>
    @GET("foods")
    fun findAllFoodAsync(): Deferred<Response<LiveData<List<Food>>>>
    @POST("foods")
    fun insertFoodAsync(@Body newUser: Food): Deferred<Response<Void>>
    @PUT("foods/{id}")
    fun updateFoodAsnc(@Path("id") id: Long, @Body newFood: Food): Deferred<Response<Void>>
    @DELETE("foods/{id}")
    fun deleteFoodAsync(@Path("id") id: Long): Deferred<Response<Void>>

    companion object {

        private val baseUrl = "http://localhost:8080/"

        fun getInstance(): FoodYizazunApiService {

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

            return retrofit.create(FoodYizazunApiService::class.java)
        }
    }
}