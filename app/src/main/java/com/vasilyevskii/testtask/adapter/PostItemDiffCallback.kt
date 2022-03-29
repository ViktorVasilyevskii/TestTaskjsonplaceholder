package com.vasilyevskii.testtask.adapter

import androidx.recyclerview.widget.DiffUtil
import com.vasilyevskii.testtask.models.PostDTO

class PostItemDiffCallback : DiffUtil.ItemCallback<PostDTO>() {

    override fun areItemsTheSame(oldItem: PostDTO, newItem: PostDTO): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PostDTO, newItem: PostDTO): Boolean {
        return oldItem == newItem
    }
}