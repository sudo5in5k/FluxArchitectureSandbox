package com.example.githubbrowserwithflux.ui.main

import com.example.githubbrowserwithflux.data.remote.entity.QiitaItem
import com.example.githubbrowserwithflux.flux.Action
import com.example.githubbrowserwithflux.util.Result

sealed class MainAction: Action {

    data class FetchItems(val result: Result<List<QiitaItem>?>): MainAction()
}