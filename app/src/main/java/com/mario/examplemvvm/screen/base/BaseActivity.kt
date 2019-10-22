package com.mario.examplemvvm.screen.base

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.mario.examplemvvm.R
import com.mario.examplemvvm.screen.common.ToolbarController
import com.mario.examplemvvm.viewmodel.BaseViewModel
import com.mario.examplemvvm.viewmodel.ViewModelView
import kotlinx.android.synthetic.main.toolbar_view.*

const val BASE_CONTAINER = R.id.frame_layout

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity(), ToolbarController {

    private var currentViewModel: BaseViewModel<*>? = null

    fun <V : ViewModelView> init(viewModel: BaseViewModel<V>, view: V) {
        currentViewModel = ViewModelProviders.of(this)[viewModel::class.java]
        (currentViewModel as BaseViewModel<V>).init(view)
    }

    override fun setToolbarTitle(title: String) {
        toolbar_title.text = title
    }

    protected fun routeToFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(BASE_CONTAINER, fragment).commit()
    }
}