package com.example.yizazun.services

import com.example.yizazun.data.entity.Food
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

interface FoodApiServices {
    @GET("foods/{id}")
    fun findFoodsByIdAsync(@Path("id") id:Int): Deferred<Response<Food>>
    @GET("foods")
    fun findAllFoodsAsync(): Deferred<Response<List<Food>>>

    @POST("foods")
    fun insertFoodsAsync(@Body food: Food): Deferred<Response<Void>>

    @PUT("foods/{id}")
    fun updateFoodsAsync(@Path("id") id:Int, @Body food: Food): Deferred<Response<Void>>

    @DELETE("foods/{id}")
    fun deleteFoodsByIdAsync(@Path("id") id:Int): Deferred<Response<Void>>

    companion object{
        private val baseUrl = ""

        fun getInstance():FoodApiServices{
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
            return retrofit.create(FoodApiServices::class.java)
        }
    }
}