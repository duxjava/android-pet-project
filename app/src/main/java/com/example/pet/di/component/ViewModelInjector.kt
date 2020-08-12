package com.example.pet.di.component

import com.example.pet.di.module.NetworkModule
import com.example.pet.viewmodel.MainActivityModel
import com.example.pet.viewmodel.CreatePostModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    fun inject(mainActivityModel: MainActivityModel)
    fun inject(createPostModel: CreatePostModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}