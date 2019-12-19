package com.mario.examplemvvm.screen.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.mario.core.domain.data.UserData
import com.mario.examplemvvm.R
import com.mario.examplemvvm.extension.inflateLayout
import com.mario.examplemvvm.extension.loadFromUrlRoundedCorners
import com.mario.examplemvvm.screen.base.BaseFragment
import kotlinx.android.synthetic.main.detail_layout.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment(), DetailView {

    private val detailViewModel: DetailViewModel by viewModel()

    private val dataObserver = Observer<UserData> { user ->
        drawUser(user)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflateLayout(R.layout.detail_layout)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        init(detailViewModel, this)
        detailViewModel.userLiveData.observe(this, dataObserver)

        val userID = arguments?.let { DetailFragmentArgs.fromBundle(it).userID } ?: ""
        detailViewModel.getUserDetail(userID)
    }

    override fun showErrorToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
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