package com.example.hema.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hema.databinding.ActivityMainBinding
import com.example.hema.domain.model.News
import com.example.hema.presentation.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var viewModel: NewsViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: RecyclerAdapter
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView = binding.recyclerView
        recyclerAdapter = RecyclerAdapter(this)
        recyclerView.adapter = recyclerAdapter
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        viewModel.getNews()
        viewModel.newsList.observe(this,{
            it -> recyclerAdapter.setList(it as MutableList<News>)
        })
    }


}