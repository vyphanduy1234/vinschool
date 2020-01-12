package com.example.vinschoolattendance.models.pojos

import com.google.gson.annotations.SerializedName

class SlotResponse {
    @SerializedName("name")
    var name: String

    @SerializedName("id")
    var id: Int

    @SerializedName("time_start")
    var timeStart: String

    @SerializedName("time_finish")
    var timeFinish: String

    constructor(name: String, id: Int, timeStart: String, timeFinish: String) {
        this.name = name
        this.id = id
        this.timeStart = timeStart
        this.timeFinish = timeFinish
    }

    override fun toString(): String {
       return this.name
    }
}