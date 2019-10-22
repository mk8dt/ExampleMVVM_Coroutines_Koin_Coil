package com.mario.examplemvvm.screen.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.mario.examplemvvm.viewmodel.BaseViewModel
import com.mario.examplemvvm.viewmodel.ViewModelView
import org.koin.core.KoinComponent

open class BaseFragment : Fragment(), KoinComponent {

    private var currentViewModel: BaseViewModel<*>? = null

    fun <V : ViewModelView> init(viewModel: BaseViewModel<V>, view: V) {
        currentViewModel = ViewModelProviders.of(this)[viewModel::class.java]
        (currentViewModel as BaseViewModel<V>).init(view)
    }

    override fun onDestroy() {
        super.onDestroy()
        currentViewModel?.onDestroy()
    }
}