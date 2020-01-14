package com.example.vinschoolattendance.models.pojos

import com.example.vinschoolattendance.models.entities.Role
import com.google.gson.annotations.SerializedName

class UserResponse {
    @SerializedName("id")
    var id: Int

    @SerializedName("name")
    var name: String

    @SerializedName("class_name")
    var className: String

    @SerializedName("mail")
    var email: String

    @SerializedName("account")
    var account: String

    @SerializedName("avatar_link")
    var avatarLink: String

    @SerializedName("token")
    var token: String

    @SerializedName("roles")
    var roles: MutableList<String>

    constructor(
        id: Int,
        name: String,
        className: String,
        email: String,
        account: String,
        avatarLink: String,
        token: String,
        roles: MutableList<String>
    ) {
        this.id = id
        this.name = name
        this.className = className
        this.email = email
        this.account = account
        this.avatarLink = avatarLink
        this.token = token
        this.roles = roles
    }
}