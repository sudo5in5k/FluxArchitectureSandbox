package com.example.githubbrowserwithflux.data.remote.entity

import java.io.Serializable

data class QiitaItem(
    val id: String,
    val title: String,
    val body: String
) : Serializable