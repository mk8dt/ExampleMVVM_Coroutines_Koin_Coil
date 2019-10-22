package com.mario.examplemvvm.client

import com.mario.examplemvvm.client.data.Result
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApiClient {

    companion object {
        const val BASE_URL = "https://gorest.co.in/"
        const val ACCESS_TOKEN = "b34ChSgkq0S6vcYbtHom0Ovaqpy6I8ZqKSXa"
    }

    @GET("public-api/users")
    suspend fun getUsersListAsync(@Query("access-token") token: String): Result
}