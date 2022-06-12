package com.hitham.hema.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hitham.hema.domain.model.News
import hitham.hema.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val news = intent.getSerializableExtra("news") as News
        binding.name?.text = news.title
        binding.description.text = news.description
    }
}