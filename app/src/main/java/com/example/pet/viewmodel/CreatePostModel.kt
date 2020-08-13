package com.example.pet.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.pet.api.ApiServiceInterface
import com.example.pet.db.dao.PostDao
import com.example.pet.models.Post
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CreatePostModel(private val postDao: PostDao) : BaseViewModel() {

    @Inject
    lateinit var apiServiceInterface: ApiServiceInterface
    private var subscriptionApi: Disposable? = null
    private var subscriptionDao: Disposable? = null
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    init {
        loadingVisibility.value = View.GONE
    }

    fun createPosts(post: Post) {
        subscriptionApi = apiServiceInterface.createPost(post)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrievePostListStart() }
            .doOnTerminate { onRetrievePostListFinish() }
            .subscribe { result -> onRetrievePostListSuccess(result) }
    }

    private fun onRetrievePostListStart() {
        loadingVisibility.value = View.VISIBLE
    }

    private fun onRetrievePostListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrievePostListSuccess(post: Post) {
        subscriptionDao = Observable.just(post).subscribeOn(Schedulers.io())
            .subscribe { result -> postDao.insertAll(result) }
    }

    override fun onCleared() {
        super.onCleared()
        subscriptionApi?.dispose()
        subscriptionDao?.dispose()
    }
}