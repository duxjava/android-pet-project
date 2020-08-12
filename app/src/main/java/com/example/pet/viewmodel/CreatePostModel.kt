package com.example.pet.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.pet.adapters.PostListAdapter
import com.example.pet.api.ApiServiceInterface
import com.example.pet.models.Post
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_create_post.*
import javax.inject.Inject

class CreatePostModel : BaseViewModel() {

    @Inject
    lateinit var apiServiceInterface: ApiServiceInterface
    private lateinit var subscription: Disposable
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    init {
        loadingVisibility.value = View.GONE
    }

    fun createPosts(post: Post) {
        subscription = apiServiceInterface.createPost(post)
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

    private fun onRetrievePostListSuccess(post: Post) {
        Log.d("post_title", post.title)
    }

    private fun onRetrievePostListError() {

    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}