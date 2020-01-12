package com.example.vinschoolattendance.models.pojos

import com.google.gson.annotations.SerializedName

class RoomResponse {
    @SerializedName("name")
    var room: String

    @SerializedName("id")
    var id: Int

    constructor(room: String, id: Int) {
        this.room = room
        this.id = id
    }

    override fun toString(): String {
        return this.room
    }
}