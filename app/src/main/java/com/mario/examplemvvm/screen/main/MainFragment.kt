package com.mario.examplemvvm.screen.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mario.core.domain.data.UserData
import com.mario.examplemvvm.R
import com.mario.examplemvvm.extension.gone
import com.mario.examplemvvm.extension.inflateLayout
import com.mario.examplemvvm.extension.loadFromUrlCircle
import com.mario.examplemvvm.extension.visible
import com.mario.examplemvvm.screen.base.BaseFragment
import kotlinx.android.synthetic.main.main_layout.*
import kotlinx.android.synthetic.main.user_itemview.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment(), MainView {

    private val mainViewModel: MainViewModel by viewModel()

    private val userAdapter = UserAdapter(listOf()) { user ->
        routeToDetail(user.id)
    }

    private val dataObserver = Observer<List<UserData>> { userList ->
        userAdapter.setUserList(userList)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        container?.inflateLayout(R.layout.main_layout)

    override fun initView() {
        init(mainViewModel, this)

        recycler.run {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
        mainViewModel.userListLiveData.observe(viewLifecycleOwner, dataObserver)
    }

    private fun routeToDetail(userId: String) {
        val action = MainFragmentDirections.routeToDetail()
        action.userID = userId
        navController?.navigate(action)
    }

    override fun hideLoading() {
        progressBar.gone()
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun showErrorToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    inner class UserAdapter(private var list: List<UserData>, val onclick: (UserData) -> Unit) :
        RecyclerView.Adapter<UserAdapter.Holder>() {

        fun setUserList(currentList: List<UserData>) {
            list = currentList
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder =
            Holder(parent.inflateLayout(R.layout.user_itemview))

        override fun getItemCount(): Int = list.size

        override fun onBindViewHolder(holder: Holder, position: Int) {
            holder.apply {
                drawUser(list[position])
                itemView.setOnClickListener { onclick(list[position]) }
            }
        }

        inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun drawUser(user: UserData) {
                itemView.apply {
                    nameUserItem.text = user.firstName
                    lastNameUserItem.text = user.lastName
                    phoneUserItem.text = user.phone
                    imageUserItem.loadFromUrlCircle(user.photo)
                }
            }
        }
    }
}