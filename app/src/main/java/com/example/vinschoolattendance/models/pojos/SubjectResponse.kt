package com.example.vinschoolattendance.models.pojos

import com.google.gson.annotations.SerializedName

class SubjectResponse {
    @SerializedName("subject")
    var subject: String

    @SerializedName("id")
    var id: Int

    constructor(subject: String, id: Int) {
        this.subject = subject
        this.id = id
    }
}