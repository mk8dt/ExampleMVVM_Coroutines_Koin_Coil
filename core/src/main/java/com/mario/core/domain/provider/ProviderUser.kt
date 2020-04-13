package com.mario.core.domain.provider

import com.mario.core.domain.*
import com.mario.core.domain.cache.UserCache
import com.mario.core.domain.data.UserData
import com.mario.core.domain.repository.RepositoryUser

class ProviderUser(
    private val repositoryUser: RepositoryUser,
    private val userCache: UserCache
) {

    suspend fun getUserList(): Either<String, List<UserData>> {
        val dataCache = userCache.load()
        return if (dataCache != null) value(dataCache)
        else {
            val dataRepository = repositoryUser.getUserList()
            dataRepository.fold({}, { userCache.save(it) })
            dataRepository
        }
    }

    suspend fun getUserById(userId: String): Either<String, UserData?> {
        return getUserList() flatMap { list ->
            val user = list.find { it.id == userId }
            either { user } mapError { exception -> exception.localizedMessage }
        }
    }
}