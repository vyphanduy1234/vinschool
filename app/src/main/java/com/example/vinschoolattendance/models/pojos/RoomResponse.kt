package com.example.vinschoolattendance.models.pojos

import com.google.gson.annotations.SerializedName

class RoomResponse {
    @SerializedName("room")
    var room: String

    @SerializedName("id")
    var id: Int

    constructor(room: String, id: Int) {
        this.room = room
        this.id = id
    }
}