package com.example.pet.viewmodel

import androidx.lifecycle.ViewModel
import com.example.pet.di.component.DaggerViewModelInjector
import com.example.pet.di.component.ViewModelInjector

abstract class BaseViewModel : ViewModel() {

    private val inject: ViewModelInjector = DaggerViewModelInjector.create()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is MainActivityModel -> inject.inject(this)
            is CreatePostModel -> inject.inject(this)
        }
    }
}