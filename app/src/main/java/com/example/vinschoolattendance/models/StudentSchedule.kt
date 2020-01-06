package com.appsnipp.creativelogindesigns.model

import com.example.vinschoolattendance.models.FriendAttend
import com.google.gson.annotations.SerializedName

class StudentSchedule {
    @SerializedName("")
    var timeHour: String
    var timeMinute: String
    var timeAT: String
    var isAttend: Boolean
    @SerializedName("subject")
    var subject: String
    var subjectTime: String
    var friendAttends: List<FriendAttend>

    constructor(
        timeHour: String = "07",
        timeMinute: String = "00",
        timeAT: String = "AM",
        isAttend: Boolean = true,
        subject: String = "Physic",
        subjectTime: String = "1 hour 30 minutes",
        friendAttends: List<FriendAttend> = emptyList<FriendAttend>()
    ) {
        this.timeHour = timeHour
        this.timeMinute = timeMinute
        this.timeAT = timeAT
        this.isAttend = isAttend
        this.subject = subject
        this.subjectTime = subjectTime
        this.friendAttends = friendAttends
    }

}