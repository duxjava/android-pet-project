package com.example.pet.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pet.R
import com.example.pet.models.Post


class PostAdapter :
    RecyclerView.Adapter<PostAdapter.MyViewHolder>() {

    private var posts: List<Post> = emptyList()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var title: TextView = itemView.findViewById(R.id.title)
        var body: TextView = itemView.findViewById(R.id.body)

    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyViewHolder {
        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.post, parent, false)
        return MyViewHolder(textView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text = posts[position].title
        holder.body.text = posts[position].body
    }

    override fun getItemCount() = posts.size

    fun updatePosts(posts: List<Post>) {
        this.posts = posts
        notifyDataSetChanged()
    }

}