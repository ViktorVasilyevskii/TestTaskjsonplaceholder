package com.vasilyevskii.testtask.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vasilyevskii.testtask.databinding.CommentPostItemAdapterBinding
import com.vasilyevskii.testtask.models.CommentPostDTO

class CommentPostAdapter : ListAdapter<CommentPostDTO, CommentPostAdapter.CommentViewHolder>(CommentItemDiffCallBack()) {

        inner class CommentViewHolder(
            val binding: CommentPostItemAdapterBinding
        ): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        return CommentViewHolder(
            CommentPostItemAdapterBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val commentItem = getItem(position)

        holder.binding.apply {
            nameComment.text = commentItem.name
            emailComment.text = commentItem.email
            bodyComment.text = commentItem.body
        }
    }
}