package com.newsapp.feature.main

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.newsapp.R
import com.newsapp.application.NewsApplication
import com.newsapp.feature.base.NewsBaseActivity
import com.newsapp.feature.shared.model.Articles
import com.newsapp.utils.util.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class NewsActivity : NewsBaseActivity<NewsView, NewsPresenter>(), NewsView, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var newsPresenter: NewsPresenter

    @Inject
    lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as NewsApplication).getNewsComponent().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initSwipeRefreshLayout()
        initRecyclerView()
        presenter.getArticles()
    }

    override fun createPresenter() = newsPresenter

    private fun initRecyclerView() {
        news_list_recycler_view.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        news_list_recycler_view.itemAnimator = DefaultItemAnimator()
        val dividerDrawable = ContextCompat.getDrawable(this, R.drawable.divider_drawable)
        dividerDrawable?.let { DividerItemDecoration(it) }?.let {
            news_list_recycler_view.addItemDecoration(
                it
            )
        }
//        news_list_recycler_view.setHasFixedSize(true)
        news_list_recycler_view.adapter = newsAdapter
    }

    private fun initSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(this)
    }

    override fun populateArticles(articlesList: List<Articles>) {
        newsAdapter.setData(articlesList)
    }

    override fun onRefresh() {
        presenter.getArticles()
    }
}
