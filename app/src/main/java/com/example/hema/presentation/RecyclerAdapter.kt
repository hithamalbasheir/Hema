package com.example.hema.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.example.hema.databinding.ListRowBinding
import com.example.hema.domain.model.News
import com.squareup.picasso.Picasso


class RecyclerAdapter(private val context: Context, private val onClickListener: MyViewHolder.OnClickListener) : RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {
    private var newsList = mutableListOf<News>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ListRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.author.text = newsList[position].author
        holder.title.text = newsList[position].title
        Picasso.with(context).load(newsList[position].urlToImage).into(holder.image)
        holder.itemView.setOnClickListener{onClickListener.onItemClicked(news= newsList[position])}
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    fun setList(newsList: MutableList<News>) {
        this.newsList = newsList
        notifyDataSetChanged()
    }

    class MyViewHolder(private val binding: ListRowBinding):RecyclerView.ViewHolder(binding.root){
        val author = binding.author
        val title = binding.title
        val image = binding.image

        interface OnClickListener{
            fun onItemClicked(news: News)
        }
    }
}