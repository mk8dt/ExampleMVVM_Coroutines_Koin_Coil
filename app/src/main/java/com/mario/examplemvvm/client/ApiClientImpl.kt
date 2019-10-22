package com.mario.examplemvvm.client

import com.mario.core.client.ApiClient
import com.mario.core.domain.Either
import com.mario.core.domain.data.UserData
import com.mario.core.domain.either
import com.mario.core.domain.mapError
import com.mario.examplemvvm.client.data.User

class ApiClientImpl(
    private val retrofitApiClient: RetrofitApiClient
) : ApiClient {

    override suspend fun getUsersList(): Either<String, List<UserData>> {
        val data = retrofitApiClient.getUsersListAsync(RetrofitApiClient.ACCESS_TOKEN).userList.map { user ->
            User(user.id, user.firstName, user.lastName, user.birthday, user.email, user.phone, user.address, user.links)
        }
        return either { data } mapError { exception -> exception.localizedMessage }
    }
}