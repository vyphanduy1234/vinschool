package com.appsnipp.creativelogindesigns.model

import androidx.room.Entity
import com.example.vinschoolattendance.models.entities.FriendAttend

@Entity(tableName = "StudentSchedule")
class StudentSchedule {
    var timeStartHour: Int
    var timeStartMinute: Int
    var timeStartAT: String
    var isAttend: Boolean
    var subject: String
    var subjectTime: String
    var friendAttends: List<FriendAttend>
    var cclass: String

    constructor(
        timeHour: Int = 7,
        timeMinute: Int = 0,
        timeAT: String = "AM",
        isAttend: Boolean = true,
        subject: String = "Physic",
        cclass: String = "IS1000",
        subjectTime: String = "1 hour 30 minutes",
        friendAttends: List<FriendAttend> = emptyList<FriendAttend>()
    ) {
        this.timeStartHour = timeHour
        this.timeStartMinute = timeMinute
        this.timeStartAT = timeAT
        this.isAttend = isAttend
        this.subject = subject
        this.subjectTime = subjectTime
        this.friendAttends = friendAttends
        this.cclass = cclass
    }
}