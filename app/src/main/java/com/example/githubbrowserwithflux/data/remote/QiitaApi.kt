package com.example.githubbrowserwithflux.data.remote

import com.example.githubbrowserwithflux.data.remote.entity.QiitaItem
import retrofit2.Response
import retrofit2.http.GET

interface QiitaApi {

    @GET("items")
    suspend fun getItems(): Response<List<QiitaItem>>

    companion object {
        const val BASE_URL = "https://qiita.com/api/v2/"
    }
}