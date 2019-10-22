package com.mario.examplemvvm.client.data

import com.google.gson.annotations.SerializedName
import com.mario.core.domain.data.UserData

data class User(
    override val id: String,
    @SerializedName("first_name")
    override val firstName: String,
    @SerializedName("last_name")
    override val lastName: String,
    @SerializedName("dob")
    override val birthday: String,
    override val email: String,
    override val phone: String,
    override val address: String,
    @SerializedName("_links")
    val links: Links
) : UserData {

    override val photo: String
        get() = links.avatar.photo
}

data class Links(
    val avatar: Avatar
)

data class Avatar(
    @SerializedName("href")
    val photo: String
)