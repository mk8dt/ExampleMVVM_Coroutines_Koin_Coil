package com.mario.examplemvvm.screen.detail

import android.os.Bundle
import androidx.lifecycle.Observer
import com.mario.core.domain.data.UserData
import com.mario.examplemvvm.R
import com.mario.examplemvvm.extension.loadFromUrlRoundedCorners
import com.mario.examplemvvm.screen.base.BaseActivity
import kotlinx.android.synthetic.main.detail_layout.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : BaseActivity(), DetailView {

    private val detailViewModel: DetailViewModel by viewModel()

    private val dataObserver = Observer<UserData> { user ->
        drawUser(user)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_layout)
        initView()
    }

    private fun initView() {
        init(detailViewModel, this)
        detailViewModel.userLiveData.observe(this, dataObserver)
    }

    override fun drawUser(user: UserData) {
        user_image_detail.loadFromUrlRoundedCorners(user.photo)
        user_first_name_detail.text = user.firstName
        user_second_name_detail.text = user.lastName
        user_birthday_detail.text = user.birthday
        user_email_detail.text = user.email
        user_phone_detail.text = user.phone
        user_address_detail.text = user.address
    }
}