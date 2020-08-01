package com.example.githubbrowserwithflux.flux

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubbrowserwithflux.App

abstract class Store(app: App) : AndroidViewModel(app) {

    val scope = viewModelScope
}