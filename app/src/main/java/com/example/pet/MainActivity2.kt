package com.example.pet

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.pet.api.JsonPlaceholderApiService
import com.example.pet.models.Post
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val actionbar = supportActionBar
        actionbar?.setHomeButtonEnabled(true)
        actionbar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    @SuppressLint("CheckResult")
    fun sendPost(v: View) {
        val apiService = JsonPlaceholderApiService.create()

        val post = Post(
            id = null,
            userId = 1,
            title = titleText.text.toString(),
            body = bodyText.text.toString()
        )

        apiService.addPost(post).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                run {
                    Log.d("Add Post", result.title)
                }
            }, { error ->
                error.printStackTrace()
            })
    }
}