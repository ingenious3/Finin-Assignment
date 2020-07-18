package com.example.fininassignment.network

import android.os.Environment
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
    private const val BASE_URL = "https://reqres.in/api/"
    var cacheSize = 10 * 1024 * 1024 // 10 MB
    var cache = Cache(Environment.getExternalStorageDirectory(), cacheSize.toLong())
    private val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    val okHttpClient = OkHttpClient.Builder()
        .cache(cache)
        .addInterceptor(loggingInterceptor)
        .addInterceptor { chain ->
            var request = chain
                .request()
                .newBuilder()
                .header("Cache-Control", "public, max-stale=" + 45*60)
                .build()
            chain.proceed(request)
        }
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> buildApiService(serviceType: Class<T>): T {
        return retrofit.create(serviceType)
    }
}

