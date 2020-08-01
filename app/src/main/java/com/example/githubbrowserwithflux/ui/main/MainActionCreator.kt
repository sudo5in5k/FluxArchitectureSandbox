package com.example.githubbrowserwithflux.ui.main

import com.example.githubbrowserwithflux.data.QiitaRepository
import com.example.githubbrowserwithflux.flux.ActionCreator
import com.example.githubbrowserwithflux.flux.Dispatcher
import com.example.githubbrowserwithflux.util.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

@ExperimentalCoroutinesApi
class MainActionCreator(
    private val dispatcher: Dispatcher,
    private val repository: QiitaRepository
) : ActionCreator, CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.IO

    suspend fun fetchItems() = withContext(coroutineContext) {
        try {
            val items = repository.getItem()
            val action = if (items == null) {
                MainAction.FetchItems(Result.Failure("Value is Null"))
            } else {
                MainAction.FetchItems(Result.Success(items))
            }
            dispatcher.dispatch(action)
        } catch (e: Exception) {
            val action = MainAction.FetchItems(Result.Failure(e.message ?: "Null Error Message"))
            dispatcher.dispatch(action)
        }
    }
}