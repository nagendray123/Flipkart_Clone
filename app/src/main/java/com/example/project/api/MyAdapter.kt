package com.example.project.api

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project.R

class MyAdapter(private val context: Context, var list : List<ItemsItem>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
      var image =  view.findViewById<ImageView>(R.id.imageView)
        var title = view.findViewById<TextView>(R.id.titleView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var views = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(views)
    }

    override fun getItemCount(): Int {
     return list.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(list[position].avatar_url).into(holder.image)
        holder.title.text = list[position].login
    }
}