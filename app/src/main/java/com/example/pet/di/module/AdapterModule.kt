package com.example.pet.di.module

import com.example.pet.adapters.PostListAdapter
import dagger.Module
import dagger.Provides

@Module
@Suppress("unused")
object AdapterModule {

    @Provides
    internal fun getPostListAdapter(): PostListAdapter {
        return PostListAdapter()
    }

}