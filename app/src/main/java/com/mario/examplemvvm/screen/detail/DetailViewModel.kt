package com.mario.examplemvvm.screen.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mario.core.domain.data.UserData
import com.mario.core.domain.fold
import com.mario.examplemvvm.screen.base.BaseViewModel
import com.mario.examplemvvm.usecase.GetUserByNameUseCaseSuspend
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class DetailViewModel(
    private val getUserByNameUseCase: GetUserByNameUseCaseSuspend
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
    }

    override fun onDestroy() {
        uiScope.cancel()
        ioContext.cancel()
    }

    fun getUserDetail(userId: String) {
        uiScope.launch {
            val result = uiScope.async { withContext(ioContext) { getUserByNameUseCase.execute(userId) } }

            result.await().fold(
                { message -> view?.showErrorToast(message) },
                { user -> _userLiveData.postValue(user) }
            )
        }
    }
}