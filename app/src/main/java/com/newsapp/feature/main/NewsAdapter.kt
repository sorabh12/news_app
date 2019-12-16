package com.newsapp.feature.main

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.newsapp.R
import com.newsapp.feature.shared.model.Articles
import androidx.browser.customtabs.CustomTabsIntent

class NewsAdapter : RecyclerView.Adapter<NewsViewHolder>() {
    private val articlesList: MutableList<Articles> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NewsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.news_list_item, parent, false))

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val data = articlesList[position]
        holder.title.text = data.title
        holder.description.text = data.description
        Glide.with(holder.itemView).load(data.urlToImage).into(holder.thumbnail)

        holder.itemView.setOnClickListener {
            val builder = CustomTabsIntent.Builder()
            builder.setExitAnimations(holder.itemView.context, R.anim.fade_in, R.anim.fade_out)
            builder.setToolbarColor(ContextCompat.getColor(holder.itemView.context, R.color.colorPrimary))
            val customTabsIntent: CustomTabsIntent = builder.build()
            customTabsIntent.launchUrl(holder.itemView.context, Uri.parse(data.url))
        }

    }

    override fun getItemCount() = articlesList.count()

    fun setData(articlesList: List<Articles>) {
        this.articlesList.clear()
        this.articlesList.addAll(articlesList)
        notifyDataSetChanged()
    }
}