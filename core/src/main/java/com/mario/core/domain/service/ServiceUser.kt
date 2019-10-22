package com.mario.core.domain.service

import com.mario.core.domain.Either
import com.mario.core.domain.data.UserData
import com.mario.core.domain.provider.ProviderUser

class ServiceUser(private val providerUser: ProviderUser) {

    private lateinit var userData: UserData

    suspend fun getUserList(): Either<String, List<UserData>> = providerUser.getUserList()

    suspend fun getUserById(): Either<String, UserData?> = providerUser.getUserById(userData.id)

    fun saveUser(user: UserData) {
        userData = user
    }
}