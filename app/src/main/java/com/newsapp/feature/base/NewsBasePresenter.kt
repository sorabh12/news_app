package com.newsapp.feature.base

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.hannesdorfmann.mosby3.mvp.MvpView

abstract class NewsBasePresenter<V : MvpView> : MvpBasePresenter<V>() {
}