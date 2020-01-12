package com.example.vinschoolattendance.models.pojos

import com.google.gson.annotations.SerializedName

class TermResponse {
    @SerializedName("name")
    var term: String

    @SerializedName("id")
    var id: Int

    @SerializedName("time_begin")
    var timeBegin: String

    @SerializedName("time_end")
    var timeEnd: String

    constructor(term: String, id: Int, timeBegin: String, timeEnd: String) {
        this.term = term
        this.id = id
        this.timeBegin = timeBegin
        this.timeEnd = timeEnd
    }


    override fun toString(): String {
        return this.term
    }
}