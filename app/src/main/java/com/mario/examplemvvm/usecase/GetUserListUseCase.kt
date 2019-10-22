package com.mario.examplemvvm.usecase

import com.mario.core.domain.Either
import com.mario.core.domain.data.UserData
import com.mario.core.domain.service.ServiceUser
import com.mario.examplemvvm.usecase.common.UseCaseSuspend

class GetUserListUseCase(
    private val serviceUser: ServiceUser
) : UseCaseSuspend<Either<String, List<UserData>>> {

    override suspend fun bind(): Either<String, List<UserData>> {
        return serviceUser.getUserList()
    }
}