package com.mario.examplemvvm.screen.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mario.core.domain.data.UserData
import com.mario.core.domain.fold
import com.mario.examplemvvm.usecase.GetUserUseCase
import com.mario.examplemvvm.viewmodel.BaseViewModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class DetailViewModel(
    private val getUserUseCase: GetUserUseCase
) : BaseViewModel<DetailView>() {

    private val _userLiveData = MutableLiveData<UserData>()
    private lateinit var job: Job
    private lateinit var uiScope: CoroutineScope
    private lateinit var ioContext: CoroutineContext

    val userLiveData: LiveData<UserData>
        get() = _userLiveData

    override fun onCreate() {
        job = Job()
        uiScope = CoroutineScope(Dispatchers.Main + job)
        ioContext = Dispatchers.IO + job
        getUserDetail()
    }

    override fun onDestroy() {
        uiScope.cancel()
        ioContext.cancel()
    }

    private fun getUserDetail() {

        uiScope.launch {
            val result = uiScope.async { withContext(ioContext) { getUserUseCase.bind() } }

            result.await().fold(
                { message -> print(message) },
                { user -> _userLiveData.postValue(user) }
            )
        }
    }
}