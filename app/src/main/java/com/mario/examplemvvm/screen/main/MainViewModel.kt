package com.mario.examplemvvm.screen.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mario.core.domain.data.UserData
import com.mario.core.domain.fold
import com.mario.examplemvvm.screen.base.BaseViewModel
import com.mario.examplemvvm.usecase.GetUserListUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val getUserListUseCase: GetUserListUseCase
) : BaseViewModel<MainView>() {

    private val _userListLiveData = MutableLiveData<List<UserData>>()

    val userListLiveData: LiveData<List<UserData>>
        get() = _userListLiveData

    override fun onCreate() {
        super.onCreate()
        getUserList()
    }

    private fun getUserList() {
        view?.showLoading()

        uiScope.launch {

            val result = uiScope.async { withContext(ioContext) { getUserListUseCase.execute() } }

            view?.hideLoading()

            result.await().fold(
                { message -> view?.showErrorToast(message) },
                { userList -> _userListLiveData.postValue(userList) }
            )
        }
    }
}