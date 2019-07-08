package com.advancedmobileprogramming.yizazun.model.network

import com.advancedmobileprogramming.yizazun.model.entities.User
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

interface UserApiService {
    @GET("users/{id}")
    fun getUserById(@Path("id") id:Long): Deferred<Response<User>>

    @GET("users/")
    fun getUsers(): Deferred<Response<List<User>>>


    @POST("users")
    fun postUsers(newUser: User):Deferred<Response<Void>>

    @PUT("users/{id}")
    fun updateUsers(@Path("id") id: Long, @Body user: User):Deferred<Response<User>>

    @DELETE("users/{id}")
    fun deleteUsers(@Path("id") id:Long):Deferred<Response<Void>>

    companion object {

        private val baseUrl = "http://localhost:8080/"

        fun getInstance(): UserApiService {

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

            return retrofit.create(UserApiService::class.java)
        }
    }
}