package com.mario.examplemvvm.screen.common

import android.content.Context
import android.content.Intent
import com.mario.examplemvvm.screen.detail.DetailActivity
import org.koin.core.KoinComponent
import org.koin.core.get

class RouterImpl(private val context: Context) : RouterController, KoinComponent {

    override fun routeToDetailActivity(finish: Boolean) {
        context.startActivity(Intent(get(), DetailActivity::class.java))
    }
}