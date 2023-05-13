package com.example.hema.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hema.R
import com.example.hema.databinding.ActivityMainBinding
import com.example.hema.domain.model.News
import com.example.hema.presentation.viewmodel.NewsViewModel
import com.example.hema.util.InternetConnection
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),RecyclerAdapter.MyViewHolder.OnClickListener {
    @Inject
    lateinit var connectivityManager: com.example.hema.util.ConnectivityManager
    private lateinit var internetConnection: InternetConnection
    private lateinit var viewModel: NewsViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: RecyclerAdapter
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
        internetConnection= InternetConnection(this)
        internetConnection.observe(this) { isNetworkAvailable ->
            if (isNetworkAvailable) {
                viewModel.getNewsForCaching()
                viewModel.cachingList.observe(this) {
                    viewModel.deleteNews()
                    viewModel.insertNews(it)
                }
                viewModel.getNews()
                viewModel.newsList.observe(this) {
                    recyclerAdapter.setList(it as MutableList<News>)
                }
            } else {
                viewModel.getCachedNews()
                viewModel.cachedList.observe(this) {
                    recyclerAdapter.setList(it as MutableList<News>)
                    Log.d("shiiit", "onCreate: $it")
                }
                Toast.makeText(this, "no internet", Toast.LENGTH_LONG).show()
                Log.d("TAG", "onCreate: no internet ")
            }
        }
    }
    private var doubleBackToExitPressedOnce = false
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }
        doubleBackToExitPressedOnce = true
        Toast.makeText(this, R.string.onBackPressed, Toast.LENGTH_SHORT).show()
        Handler(Looper.getMainLooper()).postDelayed({ doubleBackToExitPressedOnce = false }, 2690)
    }
    override fun onDestroy() {
        viewModel.onDestroy()
        connectivityManager.unregisterConnectionObserver(this)
        super.onDestroy()
    }

    override fun onItemClicked(news: News) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("news",news)
        startActivity(intent)
    }
}