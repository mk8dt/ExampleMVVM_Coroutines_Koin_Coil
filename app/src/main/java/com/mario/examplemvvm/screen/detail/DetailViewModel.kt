package com.mario.examplemvvm.screen.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mario.core.domain.data.UserData
import com.mario.core.domain.fold
import com.mario.examplemvvm.screen.base.BaseViewModel
import com.mario.examplemvvm.usecase.GetUserByNameUseCaseSuspend
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel(
    private val getUserByNameUseCase: GetUserByNameUseCaseSuspend
) : BaseViewModel<DetailView>() {

    private val _userLiveData = MutableLiveData<UserData>()
    val userLiveData: LiveData<UserData>
        get() = _userLiveData

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