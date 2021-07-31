package com.example.hema.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hema.databinding.ActivityMainBinding
import com.example.hema.domain.model.News
import com.example.hema.presentation.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),RecyclerAdapter.MyViewHolder.OnClickListener {
    lateinit var viewModel: NewsViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: RecyclerAdapter
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView = binding.recyclerView
        recyclerAdapter = RecyclerAdapter(this,this)
        recyclerView.adapter = recyclerAdapter
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        viewModel.getNews()
        viewModel.newsList.observe(this,{
            it -> recyclerAdapter.setList(it as MutableList<News>)
        })
    }

    override fun onDestroy() {
        viewModel.onDestroy()
        super.onDestroy()
    }

    override fun onItemClicked(news: News) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("news",news)
        startActivity(intent)
    }
}