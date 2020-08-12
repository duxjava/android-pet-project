package com.example.pet

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pet.adapters.PostAdapter
import com.example.pet.api.JsonPlaceholderApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: PostAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewManager = LinearLayoutManager(this)
        viewAdapter = PostAdapter()

        recyclerView = findViewById<RecyclerView>(R.id.postsRecyclerView).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        val apiService = JsonPlaceholderApiService.create()

        apiService.getPostAll().observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                run {
                    viewAdapter.updatePosts(result)
                }
            }, { error ->
                error.printStackTrace()
            })
    }

    fun addPost(v: View) {
        val i = Intent(this, MainActivity2::class.java)
        startActivity(i)
    }
}