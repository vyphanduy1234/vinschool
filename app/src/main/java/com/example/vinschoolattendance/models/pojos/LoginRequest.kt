package com.example.vinschoolattendance.models.pojos

import com.google.gson.annotations.SerializedName

class LoginRequest {
    @SerializedName("account")
    var account: String
    @SerializedName("password")
    var password: String

    constructor(account: String = "", password: String = "") {
        this.account = account
        this.password = password
    }
}