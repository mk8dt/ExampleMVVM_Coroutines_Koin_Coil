package com.mario.core.client

import com.mario.core.domain.Either
import com.mario.core.domain.data.UserData

interface ApiClient {

    suspend fun getUsersList(): Either<String, List<UserData>>

}