package com.newsapp.feature.base

import android.os.Bundle
import com.hannesdorfmann.mosby3.mvp.MvpActivity
import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.newsapp.R
import com.newsapp.utils.util.NewsProgressDialogHelper

abstract class NewsBaseActivity<V : MvpView, P : MvpPresenter<V>> : MvpActivity<V, P>(), NewsBaseView {
    private var dialogHelper: NewsProgressDialogHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
//        injectDependencies()
        super.onCreate(savedInstanceState)
    }

    private fun injectDependencies(){
//        (application as CustomApplication).getApplicationComponent().inject(this)
    }

    override fun showLoading() {
        if (dialogHelper == null)
            dialogHelper = NewsProgressDialogHelper(this)
        dialogHelper?.show(getString(R.string.loading))
    }

    override fun hideLoading() {
        if (dialogHelper != null)
            dialogHelper?.dismiss()
    }

    override fun onDestroy() {
        if (dialogHelper == null) {
            dialogHelper?.destroy()
        }
        super.onDestroy()
    }
}