package com.advancedmobileprogramming.yizazun.model.network

import com.advancedmobileprogramming.yizazun.model.entities.Comment
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

interface CommentApiService {
    @GET("comments/{id}")
    fun getCommentsById(@Path("id") id:Long): Deferred<Response<Comment>>

    @GET("comments/")
    fun getComments(): Deferred<Response<List<Comment>>>


    @POST("comments")
    fun postComments(newComment: Comment): Deferred<Response<Void>>

    @PUT("comments/{id}")
    fun updateComments(@Path("id") id:Long): Deferred<Response<Void>>

    @DELETE("comments/{id}")
    fun deleteComments(@Path("id") id:Long): Deferred<Response<Void>>

    companion object {

        private val baseUrl = "http://localhost:8080/"

        fun getInstance(): CommentApiService {

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

            return retrofit.create(CommentApiService::class.java)
        }
    }
}