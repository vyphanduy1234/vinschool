package com.example.vinschoolattendance.models.pojos

import com.appsnipp.creativelogindesigns.model.StudentSchedule
import com.example.vinschoolattendance.models.models.FriendAttend
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

class StudentSchedulePojo {
    @SerializedName("timeStart")
    var timeStart: String
    @SerializedName("timeFinish")
    var timeFinish: String
    @SerializedName("isAttend")
    var isAttend: Boolean
    @SerializedName("subject")
    var subject: String
    @SerializedName("room")
    var room: String
    var subjectTime: String
    //var friendAttends: MutableList<FriendAttend>
    @SerializedName("classname")
    var cclass: String

    constructor(
        timeStart: String = "07",
        timeFinish: String = "00",
        isAttend: Boolean = true,
        subject: String = "Physic",
        cclass: String = "IS1000",
        room: String = "314",
        subjectTime: String = "1 hour 30 minutes"
        //friendAttends: MutableList<FriendAttend>? = mutableListOf()
    ) {
        this.timeStart = timeStart
        this.timeFinish = timeFinish
        this.isAttend = isAttend
        this.subject = subject
        this.subjectTime = subjectTime
        //this.friendAttends = friendAttends
        this.cclass = cclass
        this.room = room
    }

    open fun toStudentSchedule(): StudentSchedule?{
        val sc = StudentSchedule()
        sc.cclass = this.cclass
        //sc.friendAttends = this.friendAttends?.toMutableList()
        sc.isAttend = this.isAttend
        sc.subject = this.subject
        sc.timeStartHour = LocalDateTime.parse(this.timeStart.substring(0,19),DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)).hour
        sc.timeStartMinute = LocalDateTime.parse(this.timeStart.substring(0,19),DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)).minute
        sc.timeStartAT = if(sc.timeStartHour < 12) "AM" else "PM"
        sc.subjectTime = "1 hour 30 minutes"

        return sc
    }
}