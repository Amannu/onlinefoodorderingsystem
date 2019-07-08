package com.advancedmobileprogramming.yizazun.model.network

import com.advancedmobileprogramming.yizazun.model.entities.Food
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

interface FoodApiService {
    @GET("foods/{id}")
    fun getFoodsById(@Path("id") id:Long): Deferred<Response<Food>>

    @GET("foods/")
    fun getFoods(): Deferred<Response<List<Food>>>


    @POST("foods")
    fun postFoods(newFood: Food): Deferred<Response<Void>>

    @PUT("foods/{id}")
    fun updateFoods(@Path("id") id:Long): Deferred<Response<Void>>

    @DELETE("foods/{id}")
    fun deleteFoods(@Path("id") id:Long): Deferred<Response<Void>>

    companion object {

        private val baseUrl = "http://localhost:8080/"

        fun getInstance(): FoodApiService {

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

            return retrofit.create(FoodApiService::class.java)
        }
    }
}