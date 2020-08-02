package com.example.githubbrowserwithflux.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubbrowserwithflux.App
import com.example.githubbrowserwithflux.R
import com.example.githubbrowserwithflux.data.QiitaRepository
import com.example.githubbrowserwithflux.data.remote.QiitaService
import com.example.githubbrowserwithflux.databinding.ActivityMainBinding
import com.example.githubbrowserwithflux.flux.Dispatcher
import com.example.githubbrowserwithflux.ui.main.MainActionCreator
import com.example.githubbrowserwithflux.ui.main.MainStore
import com.example.githubbrowserwithflux.ui.main.MainStoreFactory
import com.example.githubbrowserwithflux.util.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import timber.log.Timber

@FlowPreview
@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val dispatcher = Dispatcher()
    private val api = QiitaService.invoke()
    private val repository = QiitaRepository(api)

    private val creator = MainActionCreator(dispatcher, repository)
    private lateinit var store: MainStore

    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        store =
            ViewModelProvider(this, MainStoreFactory(application as App, dispatcher, creator)).get(
                MainStore::class.java
            )

        adapter = MainAdapter(this)
        adapter.onItemClicked = {
            Timber.i("clicked: ${it.id}")
        }

        binding.apply {
            recycler.layoutManager = LinearLayoutManager(this@MainActivity)
            recycler.adapter = this@MainActivity.adapter
            lifecycleOwner = this@MainActivity
        }

        store.items.observe(this, Observer {
            when (it) {
                is Result.Success -> {
                    adapter.submitList(it.value)
                }
                is Result.Failure -> {
                    Timber.i(it.msg)
                }
            }
        })
    }
}