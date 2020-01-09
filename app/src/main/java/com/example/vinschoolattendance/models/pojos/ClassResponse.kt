package com.example.vinschoolattendance.models.pojos

import com.google.gson.annotations.SerializedName

class ClassResponse {
    @SerializedName("class")
    var cclass: String

    @SerializedName("id")
    var id: Int

    constructor(cclass: String, id: Int) {
        this.cclass = cclass
        this.id = id
    }
}