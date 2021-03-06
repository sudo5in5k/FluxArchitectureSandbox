package com.example.githubbrowserwithflux.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.githubbrowserwithflux.App
import com.example.githubbrowserwithflux.data.remote.entity.QiitaItem
import com.example.githubbrowserwithflux.flux.Dispatcher
import com.example.githubbrowserwithflux.flux.Store
import com.example.githubbrowserwithflux.util.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.launch

@FlowPreview
@ExperimentalCoroutinesApi
class MainStore(
    app: App,
    dispatcher: Dispatcher,
    creator: MainActionCreator
) : Store(app) {

    init {
        scope.launch {
            creator.fetchItems()
        }
    }

    val items: LiveData<Result<List<QiitaItem>?>> = dispatcher.event.asFlow()
        .mapNotNull { it as? MainAction.FetchItems }
        .mapNotNull { it.result }
        .asLiveData(scope.coroutineContext)
}