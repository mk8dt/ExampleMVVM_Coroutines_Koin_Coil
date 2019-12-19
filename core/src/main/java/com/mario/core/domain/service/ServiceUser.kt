package com.mario.core.domain.service

import com.mario.core.domain.Either
import com.mario.core.domain.data.UserData
import com.mario.core.domain.provider.ProviderUser

class ServiceUser(private val providerUser: ProviderUser) {

    suspend fun getUserList(): Either<String, List<UserData>> = providerUser.getUserList()

    suspend fun getUserById(userId: String): Either<String, UserData?> = providerUser.getUserById(userId)

}