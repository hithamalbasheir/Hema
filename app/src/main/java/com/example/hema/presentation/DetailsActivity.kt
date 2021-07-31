package com.example.hema.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.hema.R
import com.example.hema.databinding.ActivityDetailsBinding
import com.example.hema.databinding.ActivityMainBinding
import com.example.hema.domain.model.News

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val news = intent.getSerializableExtra("news") as News
        binding.name.text = news.title
        binding.description.text = news.description
    }
}