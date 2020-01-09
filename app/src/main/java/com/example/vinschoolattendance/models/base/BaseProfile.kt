package com.example.vinschoolattendance.models.base

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

open class BaseProfile {
    @ColumnInfo(name = "class")
    @SerializedName("class")
    var cclass: String

    @ColumnInfo(name = "email")
    @SerializedName("email")
    var email: String

    @ColumnInfo(name = "account")
    @SerializedName("account")
    var account: String

    @ColumnInfo(name = "avatar_link")
    @SerializedName("avatar_link")
    var avatarLink: String

    @ColumnInfo(name = "name")
    @SerializedName("name")
    var name: String

    constructor(cclass: String, email: String, account: String, avatarLink: String, name: String) {
        this.cclass = cclass
        this.email = email
        this.account = account
        this.avatarLink = avatarLink
        this.name = name
    }

}