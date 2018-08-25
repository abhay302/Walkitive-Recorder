package com.example.abhay.walkitiverecorder.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val client: ApiService by lazy {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        val builder = Retrofit.Builder()
                .baseUrl("https://spotify-staging.herokuapp.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
        val retrofit = builder.build()

        retrofit.create(ApiService::class.java)
    }
}