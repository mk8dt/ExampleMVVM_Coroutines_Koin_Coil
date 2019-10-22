package com.mario.examplemvvm.client.data

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("result")
    val userList: List<User>
)