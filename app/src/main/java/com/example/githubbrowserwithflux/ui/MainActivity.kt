package com.example.githubbrowserwithflux.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.githubbrowserwithflux.App
import com.example.githubbrowserwithflux.R
import com.example.githubbrowserwithflux.data.QiitaRepository
import com.example.githubbrowserwithflux.data.remote.QiitaService
import com.example.githubbrowserwithflux.flux.Dispatcher
import com.example.githubbrowserwithflux.ui.main.MainActionCreator
import com.example.githubbrowserwithflux.ui.main.MainStore
import com.example.githubbrowserwithflux.util.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private lateinit var store: MainStore
    private lateinit var creator: MainActionCreator
    private val dispatcher = Dispatcher()
    private val api = QiitaService.invoke()
    private val repository = QiitaRepository(api)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        store = MainStore(application as App, dispatcher)
        creator = MainActionCreator(dispatcher, repository)
        lifecycleScope.launchWhenCreated {
            creator.fetchItems()
        }

        store.items.observe(this, Observer {
            when (it) {
                is Result.Success -> {
                    Timber.i("${it.value ?: "Null Items"}")
                }
                is Result.Failure -> {
                    Timber.i(it.msg)
                }
            }
        })
    }
}