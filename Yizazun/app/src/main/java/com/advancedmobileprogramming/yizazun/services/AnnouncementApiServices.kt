package com.example.yizazun.services

import com.example.yizazun.data.entity.Announcement
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

interface AnnouncementApiServices {
    @GET(" announcements/{id}")
    fun findAnnouncementsByIdAsync(@Path("id") id:Int): Deferred<Response<Announcement>>
    @GET("announcements")
    fun findAllAnnouncementsAsync(): Deferred<Response<List< Announcement>>>

    @POST("announcements")
    fun insertAnnouncementAsync(@Body announcement: Announcement): Deferred<Response<Void>>

    @PUT("announcements/{id}")
    fun updateAnnouncementsAsync(@Path("id") id:Int, @Body announcement: Announcement): Deferred<Response<Void>>

    @DELETE("announcements/{id}")
    fun deleteAnnouncementsByIdAsync(@Path("id") id:Int): Deferred<Response<Void>>

    companion object{
        private val baseUrl = ""

        fun getInstance():AnnouncementApiServices{
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
            return retrofit.create(AnnouncementApiServices::class.java)
        }
    }
}