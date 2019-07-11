package com.example.yizazun.services

import com.example.yizazun.data.entity.Drink
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

interface DrinkApiServices {
    @GET("drinks/{id}")
    fun findDrinksByIdAsync(@Path("id") id:Int): Deferred<Response<Drink>>
    @GET("drinks")
    fun findAllDrinksAsync(): Deferred<Response<List<Drink>>>

    @POST("drinks")
    fun insertDrinksAsync(@Body drink: Drink): Deferred<Response<Void>>

    @PUT("drinks/{id}")
    fun updateDrinksAsync(@Path("id") id:Int, @Body drink: Drink): Deferred<Response<Void>>

    @DELETE("drinks/{id}")
    fun deleteDrinksByIdAsync(@Path("id") id:Int): Deferred<Response<Void>>

    companion object{
        private val baseUrl = ""

        fun getInstance():DrinkApiServices{
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
            return retrofit.create(DrinkApiServices::class.java)
        }
    }
}