package com.example.githubbrowserwithflux.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubbrowserwithflux.App
import com.example.githubbrowserwithflux.flux.Dispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
class MainStoreFactory(
    private val app: App,
    private val dispatcher: Dispatcher,
    private val creator: MainActionCreator
) : ViewModelProvider.AndroidViewModelFactory(app) {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainStore(app, dispatcher, creator) as T
    }
}