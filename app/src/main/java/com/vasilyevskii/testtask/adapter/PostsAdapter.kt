package com.vasilyevskii.testtask.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vasilyevskii.testtask.databinding.PostItemAdapterBinding
import com.vasilyevskii.testtask.fragments.ActivityCardCommentPost
import com.vasilyevskii.testtask.models.PostDTO
import com.vasilyevskii.testtask.utils.Constants.BODY_POST
import com.vasilyevskii.testtask.utils.Constants.ID_POST
import com.vasilyevskii.testtask.utils.Constants.TITLE_POST

class PostsAdapter : ListAdapter<PostDTO, PostsAdapter.PostViewHolder>(PostItemDiffCallback()) {

    inner class PostViewHolder(
        val binding: PostItemAdapterBinding
    ): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder{
        return PostViewHolder(
            PostItemAdapterBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val postItem = getItem(position)

        holder.binding.apply {
            titlePost.text = postItem.title
            bodyPost.text = postItem.body
            this.root.setOnClickListener {
                val intent = Intent(it.context, ActivityCardCommentPost::class.java)
                intent.apply {
                    putExtra(ID_POST, postItem.id)
                    putExtra(TITLE_POST, postItem.title)
                    putExtra(BODY_POST, postItem.body)
                }
                this.root.context.startActivity(intent)
            }
        }
    }

}