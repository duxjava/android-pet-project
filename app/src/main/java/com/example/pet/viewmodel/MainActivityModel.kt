package com.example.pet.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.pet.adapters.PostListAdapter
import com.example.pet.api.ApiServiceInterface
import com.example.pet.db.dao.PostDao
import com.example.pet.models.Post
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivityModel(private val postDao: PostDao) : BaseViewModel() {

    @Inject
    lateinit var apiServiceInterface: ApiServiceInterface
    private lateinit var subscription: Disposable
    private val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val postListAdapter: PostListAdapter = PostListAdapter()

    init {
        loadPosts()
    }

    private fun loadPosts() {
        subscription = Observable.fromCallable { postDao.all }
            .concatMap {
                    dbPostList ->
                if(dbPostList.isEmpty())
                    apiServiceInterface.getPosts().concatMap {
                            apiPostList -> postDao.insertAll(*apiPostList.toTypedArray())
                        Observable.just(apiPostList)
                    }
                else
                    Observable.just(dbPostList)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrievePostListStart() }
            .doOnTerminate { onRetrievePostListFinish() }
            .subscribe(
                { result -> onRetrievePostListSuccess(result) },
                { onRetrievePostListError() }
            )
    }

    private fun onRetrievePostListStart() {
        loadingVisibility.value = View.VISIBLE
    }

    private fun onRetrievePostListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrievePostListSuccess(posts: List<Post>) {
        postListAdapter.updatePosts(posts)
    }

    private fun onRetrievePostListError() {

    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}