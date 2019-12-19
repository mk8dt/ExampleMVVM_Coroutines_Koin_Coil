package com.mario.examplemvvm.screen.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mario.core.domain.data.UserData
import com.mario.core.domain.fold
import com.mario.examplemvvm.screen.base.BaseViewModel
import com.mario.examplemvvm.usecase.GetUserListUseCase
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainViewModel(
    private val getUserListUseCase: GetUserListUseCase
) : BaseViewModel<MainView>() {

    private val _userListLiveData = MutableLiveData<List<UserData>>()
    private lateinit var job: Job
    private lateinit var uiScope: CoroutineScope
    private lateinit var ioContext: CoroutineContext

    val userListLiveData: LiveData<List<UserData>>
        get() = _userListLiveData

    override fun onCreate() {
        job = Job()
        uiScope = CoroutineScope(Dispatchers.Main + job)
        ioContext = Dispatchers.IO + job
        getUserList()
    }

    override fun onDestroy() {
        uiScope.cancel()
        ioContext.cancel()
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