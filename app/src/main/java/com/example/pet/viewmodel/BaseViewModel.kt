package com.example.pet.viewmodel

import androidx.lifecycle.ViewModel
import com.example.pet.di.component.DaggerViewModelInjector
import com.example.pet.di.component.ViewModelInjector
import com.example.pet.di.module.NetworkModule

abstract class BaseViewModel : ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is MainActivityModel -> injector.inject(this)
            is CreatePostModel -> injector.inject(this)
        }
    }
}