package com.example.vinschoolattendance.models.pojos

import com.google.gson.annotations.SerializedName

class TeacherResponse {

    @SerializedName("name")
    var name: String

    @SerializedName("id")
    var id: Int

    constructor(name: String, id: Int) {
        this.name = name
        this.id = id
    }

    override fun toString(): String {
        return this.name
    }
}