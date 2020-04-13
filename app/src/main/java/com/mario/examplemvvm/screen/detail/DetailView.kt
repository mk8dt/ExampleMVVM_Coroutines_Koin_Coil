package com.mario.examplemvvm.screen.detail

import com.mario.core.domain.data.UserData
import com.mario.examplemvvm.screen.base.BaseView

interface DetailView : BaseView {

    fun drawUser(user: UserData)
}