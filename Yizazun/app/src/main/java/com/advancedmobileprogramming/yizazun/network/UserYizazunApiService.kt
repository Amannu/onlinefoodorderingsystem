package com.advancedmobileprogramming.yizazun.network


import androidx.lifecycle.LiveData
import com.advancedmobileprogramming.yizazun.model.User
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

interface UserYizazunApiService {

    @GET("users/{id}")
    fun findUserByIdAsync(@Path("id") id: Long): Deferred<Response<LiveData<User>>>
    @POST("users")
    fun insertUserAsync(@Body newUser: User): Deferred<Response<Void>>
    @PUT("users/{id}")
    fun updateUserAsnc(@Path("id") id: Long, @Body newUser: User): Deferred<Response<Void>>
    @DELETE("users/{id}")
    fun deleteUserAsync(@Path("id") id: Long): Deferred<Response<Void>>

    companion object {

        private val baseUrl = "http://localhost:8080/"

        fun getInstance(): UserYizazunApiService {

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

            return retrofit.create(UserYizazunApiService::class.java)
        }
    }
}