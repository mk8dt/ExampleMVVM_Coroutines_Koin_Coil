package com.mario.core.domain.provider

import com.mario.core.domain.Either
import com.mario.core.domain.data.UserData
import com.mario.core.domain.either
import com.mario.core.domain.flatMap
import com.mario.core.domain.mapError
import com.mario.core.domain.repository.RepositoryUser

class ProviderUser(private val repositoryUser: RepositoryUser) {

    suspend fun getUserList(): Either<String, List<UserData>> = repositoryUser.getUserList()

    suspend fun getUserById(userId: String): Either<String, UserData?> {
        return getUserList() flatMap { list ->
            val user = list.find { it.id == userId }
            either { user }  mapError { exception -> exception.localizedMessage }
        }
    }
}