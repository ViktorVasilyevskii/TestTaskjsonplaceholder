package com.vasilyevskii.testtask.adapter

import androidx.recyclerview.widget.DiffUtil
import com.vasilyevskii.testtask.models.CommentPostDTO

class CommentItemDiffCallBack : DiffUtil.ItemCallback<CommentPostDTO>() {
    override fun areItemsTheSame(oldItem: CommentPostDTO, newItem: CommentPostDTO): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CommentPostDTO, newItem: CommentPostDTO): Boolean {
        return oldItem == newItem
    }

}