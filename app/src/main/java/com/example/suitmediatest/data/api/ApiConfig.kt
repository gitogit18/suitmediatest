package com.example.suitmediatest.data.api

import com.example.suitmediatest.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiConfig {
    private const val BASE_URL = "https://reqres.in/"
    private val API_KEY = BuildConfig.REQRES_API_KEY

    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->

            val request = chain.request()
                .newBuilder()
                .addHeader("x-api-key", API_KEY)
                .build()

            chain.proceed(request)
        }
        .build()

    val apiService: UserApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserApiService::class.java)
    }
}