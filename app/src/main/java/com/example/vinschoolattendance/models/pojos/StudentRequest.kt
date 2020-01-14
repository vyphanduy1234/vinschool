package com.example.vinschoolattendance.models.pojos

import com.google.gson.annotations.SerializedName

class StudentRequest {

    @SerializedName("first_name")
    var firstName: String

    @SerializedName("last_name")
    var lastName: String

    @SerializedName("email")
    var email: String

    @SerializedName("account")
    var account: String

    @SerializedName("password")
    var password: String

    @SerializedName("class_id")
    var classId: Int

    @SerializedName("picture")
    var picture: String

    @SerializedName("role_id")
    var roleId: Int


    constructor(
        firstName: String ="",
        lastName: String="",
        email: String="",
        account: String="",
        password: String="",
        classId: Int=1,
        picture: String="https://i.imgur.com/OZCIFla.jpg",
        roleId: Int =2
    ) {
        this.firstName = firstName
        this.lastName = lastName
        this.email = email
        this.account = account
        this.password = password
        this.classId = classId
        this.picture = picture
        this.roleId = roleId
    }
}