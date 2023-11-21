package com.mkshmnv.djinni.network

import com.mkshmnv.djinni.Logger
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient(domain: String) {
    private var retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(provideOKHTTPClient())
        .baseUrl(domain)
        .build()

    private fun provideOKHTTPClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor { message -> Logger.logcat(message, "ApiClient") }
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(29, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(26, TimeUnit.SECONDS)
            .build()
    }

    fun <T> buildRetrofit(service: Class<T>): T = retrofit.create(service)
}
