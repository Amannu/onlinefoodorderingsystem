package com.advancedmobileprogramming.yizazun.model.network

import com.advancedmobileprogramming.yizazun.model.entities.Drink
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

interface DrinkApiService {
    @GET("drinks/{id}")
    fun getDrinksById(@Path("id") id:Long): Deferred<Response<Drink>>

    @GET("drinks/")
    fun getDrinks(): Deferred<Response<List<Drink>>>


    @POST("drinks")
    fun postDrinks(newDrink: Drink): Deferred<Response<Void>>

    @PUT("drinks/{id}")
    fun updateDrinks(@Path("id") id: Long, @Body drink: Drink): Deferred<Response<Drink>>

    @DELETE("drinks/{id}")
    fun deleteDrinks(@Path("id") id:Long): Deferred<Response<Void>>

    companion object {

        private val baseUrl = "http://localhost:8080/"

        fun getInstance(): DrinkApiService {

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

            return retrofit.create(DrinkApiService::class.java)
        }
    }
}