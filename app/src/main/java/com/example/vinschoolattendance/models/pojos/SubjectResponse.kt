package com.example.vinschoolattendance.models.pojos

import com.google.gson.annotations.SerializedName

class SubjectResponse {
    @SerializedName("name")
    var subject: String

    @SerializedName("id")
    var id: Int

    constructor(subject: String, id: Int) {
        this.subject = subject
        this.id = id
    }

    override fun toString(): String {
        return this.subject
    }
}