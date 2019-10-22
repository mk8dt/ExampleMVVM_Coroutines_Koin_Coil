package com.mario.examplemvvm.usecase

import com.mario.core.domain.data.UserData
import com.mario.core.domain.service.ServiceUser
import com.mario.examplemvvm.usecase.common.UseCaseWithParams

class SaveUserIdUseCase(
    private val serviceUser: ServiceUser
) : UseCaseWithParams<Unit, UserData> {

    override fun bind(params: UserData) {
        serviceUser.saveUser(params)
    }
}