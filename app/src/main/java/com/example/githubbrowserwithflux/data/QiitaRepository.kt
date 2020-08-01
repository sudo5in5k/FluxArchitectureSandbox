package com.example.githubbrowserwithflux.data

import com.example.githubbrowserwithflux.data.remote.QiitaApi
import com.example.githubbrowserwithflux.data.remote.entity.QiitaItem
import timber.log.Timber

class QiitaRepository(private val api: QiitaApi) {

    suspend fun fetchItems(): List<QiitaItem>? {
        val response = api.getItems()
        return if (response.isSuccessful) {
            response.body()
        } else {
            Timber.i("${response.errorBody()}")
            null
        }
    }
}