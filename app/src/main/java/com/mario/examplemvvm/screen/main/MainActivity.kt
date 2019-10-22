package com.mario.examplemvvm.screen.main

import android.os.Bundle
import com.mario.examplemvvm.R
import com.mario.examplemvvm.screen.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.base_layout)
        routeToFragment(MainFragment.newInstance())
    }
}