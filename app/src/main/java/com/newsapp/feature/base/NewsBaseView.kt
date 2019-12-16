package com.newsapp.feature.base

import com.hannesdorfmann.mosby3.mvp.MvpView

interface NewsBaseView: MvpView {
    fun showLoading()
    fun hideLoading()
}