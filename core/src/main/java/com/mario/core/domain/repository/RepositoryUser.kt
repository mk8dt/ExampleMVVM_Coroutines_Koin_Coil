package com.mario.core.domain.repository

import com.mario.core.client.ApiClient
import com.mario.core.domain.Either
import com.mario.core.domain.data.UserData

class RepositoryUser(private val apiClient: ApiClient) {

    suspend fun getUserList(): Either<String, List<UserData>> = apiClient.getUsersList()
}