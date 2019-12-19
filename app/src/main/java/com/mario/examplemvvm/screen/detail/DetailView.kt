package com.mario.examplemvvm.screen.detail

import com.mario.core.domain.data.UserData
import com.mario.examplemvvm.screen.base.ViewModelView

interface DetailView : ViewModelView {

    fun drawUser(user: UserData)
}