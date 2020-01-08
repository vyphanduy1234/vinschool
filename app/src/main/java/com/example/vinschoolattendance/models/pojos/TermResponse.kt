package com.example.vinschoolattendance.models.pojos

import com.google.gson.annotations.SerializedName

class TermResponse {
    @SerializedName("term")
    var term: String
    @SerializedName("id")
    var id: Int

    constructor(term: String, id: Int) {
        this.term = term
        this.id = id
    }
}