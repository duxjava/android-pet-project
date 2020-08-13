package com.example.pet

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.pet.databinding.ActivityCreatePostBinding
import com.example.pet.db.ViewModelFactory
import com.example.pet.models.Post
import com.example.pet.viewmodel.CreatePostModel
import com.example.pet.viewmodel.PostViewModel

class CreatePostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreatePostBinding
    private lateinit var viewModel: CreatePostModel
    private lateinit var postViewModel: PostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_post)
        viewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(CreatePostModel::class.java)
        postViewModel = ViewModelProviders.of(this).get(PostViewModel::class.java)
        binding.viewModel = viewModel
        binding.postViewModel = postViewModel

        binding.lifecycleOwner = this

        val actionbar = supportActionBar
        actionbar?.setHomeButtonEnabled(true)
        actionbar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    fun sendPost(v: View) {
        val post = Post(
            id = null,
            userId = 1,
            title = postViewModel.getPostTitle().value!!,
            body = postViewModel.getPostBody().value!!
        )

        viewModel.createPosts(post)
    }
}