package com.example.githubbrowserwithflux.data.remote

import androidx.viewbinding.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object QiitaService {
    operator fun invoke(): QiitaApi {
        return getRetrofit().create(QiitaApi::class.java)
    }

    private fun getRetrofit() = Retrofit.Builder()
        .baseUrl(QiitaApi.BASE_URL)
        .client(getOkHttpClient())
        .addConverterFactory(MoshiConverterFactory.create(getMoshi()))
        .build()

    private fun getMoshi() = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private fun getOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor {
                val httpUrl = it.request().url
                val requestBuilder = it.request().newBuilder().url(httpUrl)
                it.proceed(requestBuilder.build())
            }
            .apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(loggingInterceptor)
                }
            }
            .readTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
            .build()
    }
}