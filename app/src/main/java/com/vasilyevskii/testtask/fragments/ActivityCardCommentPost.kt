package com.vasilyevskii.testtask.fragments

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.vasilyevskii.testtask.R
import com.vasilyevskii.testtask.adapter.CommentPostAdapter
import com.vasilyevskii.testtask.databinding.ActivityCardCommentPostBinding
import com.vasilyevskii.testtask.utils.Constants.BODY_POST
import com.vasilyevskii.testtask.utils.Constants.ID_POST
import com.vasilyevskii.testtask.utils.Constants.TITLE_POST
import com.vasilyevskii.testtask.viewmodel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ActivityCardCommentPost :  AppCompatActivity() {

    private lateinit var binding: ActivityCardCommentPostBinding
    private lateinit var commentPostAdapter: CommentPostAdapter
    private val postViewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCardCommentPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        loadDataPutExtra()
        loadError()
    }


    private fun loadDataPutExtra(){
        val idPost = intent.getIntExtra(ID_POST, 0)
        val titlePost = intent.getStringExtra(TITLE_POST)
        val bodyPost = intent.getStringExtra(BODY_POST)
        displayDataScreen(titlePost!!, bodyPost!!)
        loadCommentPost(idPost.toString())
    }

    private fun displayDataScreen(titlePost: String, bodyPost: String){
        binding.postTitleCard.text = titlePost
        binding.postBodyCard.text = bodyPost

    }

    private fun initRecyclerView(){
        commentPostAdapter = CommentPostAdapter()
        binding.recyclerCommentPost.apply {
            layoutManager = LinearLayoutManager(this@ActivityCardCommentPost)
            setHasFixedSize(true)
            adapter = commentPostAdapter
        }
    }

    private fun loadCommentPost(idPost: String){
        postViewModel.getResponseCommentPost(idPost)

        postViewModel.responseCommentPost.observe(this){ comment ->
            commentPostAdapter.submitList(comment)
        }
    }

    private fun loadError(){
        postViewModel.responseCommentPostError.observe(this){
            Toast.makeText(this, getString(R.string.text_error_response_server), Toast.LENGTH_LONG)
                .show()
        }
    }



}