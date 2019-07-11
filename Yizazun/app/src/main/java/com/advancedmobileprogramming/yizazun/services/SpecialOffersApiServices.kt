package com.example.yizazun.services

import com.example.yizazun.data.entity.SpecialOffers
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

interface SpecialOffersApiServices {
    @GET("specialOfferses/{id}")
    fun findSpecialOffersByIdAsync(@Path("id") id:Int): Deferred<Response<SpecialOffers>>
    @GET("specialOfferses")
    fun findAllSpecialOffersAsync(): Deferred<Response<List<SpecialOffers>>>

    @POST("specialOfferses")
    fun insertSpecialOfferssAsync(@Body specialOffers: SpecialOffers): Deferred<Response<Void>>

    @PUT("specialOfferses/{id}")
    fun updateSpecialOfferssAsync(@Path("id") id:Int, @Body specialOffers: SpecialOffers): Deferred<Response<Void>>

    @DELETE("specialOfferses/{id}")
    fun deleteSpecialOffersByIdAsync(@Path("id") id:Int): Deferred<Response<Void>>

    companion object{
        private val baseUrl = ""

        fun getInstance(): SpecialOffersApiServices{
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
            return retrofit.create( SpecialOffersApiServices::class.java)
        }
    }
}