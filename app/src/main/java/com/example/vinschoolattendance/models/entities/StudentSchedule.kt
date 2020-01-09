package com.appsnipp.creativelogindesigns.model

import androidx.room.Entity

@Entity(tableName = "StudentSchedule")
class StudentSchedule {
    var timeStartHour: Int
    var timeStartMinute: Int
    var timeStartAT: String
    var isAttend: Boolean
    var subject: String
    var subjectTime: String
    var friendAttends: List<String>
    var cclass: String
    var room: String
    var teacher: String

    constructor(
        timeHour: Int = 7,
        timeMinute: Int = 0,
        timeAT: String = "AM",
        isAttend: Boolean = true,
        subject: String = "Physic",
        cclass: String = "IS1000",
        subjectTime: String = "1 hour 30 minutes",
        friendAttends: List<String> = emptyList<String>(),
        room: String = "314",
        teacher: String = "Nhat"
    ) {
        this.timeStartHour = timeHour
        this.timeStartMinute = timeMinute
        this.timeStartAT = timeAT
        this.isAttend = isAttend
        this.subject = subject
        this.subjectTime = subjectTime
        this.friendAttends = friendAttends
        this.cclass = cclass
        this.room = room
        this.teacher = teacher
    }
}