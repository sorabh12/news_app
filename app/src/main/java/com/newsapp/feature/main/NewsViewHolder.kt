package com.newsapp.feature.main

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.news_list_item.view.*

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title = itemView?.title
    val description = itemView?.description
    val thumbnail = itemView?.thumbnail
}