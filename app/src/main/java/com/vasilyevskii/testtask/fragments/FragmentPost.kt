package com.vasilyevskii.testtask.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.vasilyevskii.testtask.R
import com.vasilyevskii.testtask.adapter.PostsAdapter
import com.vasilyevskii.testtask.databinding.FragmentPostBinding
import com.vasilyevskii.testtask.viewmodel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentPost : Fragment(R.layout.fragment_post) {

    private lateinit var binding: FragmentPostBinding
    private lateinit var postsAdapter: PostsAdapter
    private val postViewModel: PostViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        loadData()
        loadError()
    }

    private fun initRecyclerView(){
        postsAdapter = PostsAdapter()
        binding.recyclerViewFrMain.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = postsAdapter
        }
    }

    private fun loadData(){
        postViewModel.getResponsePosts()

        postViewModel.responsePosts.observe(requireActivity()){ posts ->
            postsAdapter.submitList(posts)
        }
    }

    private fun loadError(){
        postViewModel.responseErrorPosts.observe(requireActivity()){ error ->
            Toast.makeText(activity, getString(R.string.text_error_response_server), Toast.LENGTH_LONG)
                .show()
        }
    }


}