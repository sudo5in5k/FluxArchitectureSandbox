package com.example.githubbrowserwithflux.flux

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel

@ExperimentalCoroutinesApi
class Dispatcher {

    val event = BroadcastChannel<Action>(1)

    suspend fun dispatch(action: Action) {
        event.send(action)
    }
}