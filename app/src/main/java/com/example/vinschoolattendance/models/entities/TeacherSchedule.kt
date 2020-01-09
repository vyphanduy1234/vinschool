package com.example.vinschoolattendance.models.entities

import androidx.room.Entity

@Entity(tableName = "TeacherSchedule")
class TeacherSchedule {
    var timeHour: Int
    var timeMinute: Int
    var timeAT: String
    var subject: String
    var room: String
    var isStart: Boolean
    var cclass: String
    var totalStudent: Int
    var attendStudent: Int
    var scheduleId: Int

    constructor(
        timeHour: Int = 7,
        timeMinute: Int = 0,
        timeAT: String = "AM",
        subject: String = "Physic",
        isStart: Boolean = false,
        cclass: String = "12A10",
        totalStudent: Int = 40,
        attendStudent: Int = 38,
        room: String = "312",
        scheduleId: Int = 1
    ) {
        this.timeHour = timeHour
        this.timeMinute = timeMinute
        this.timeAT = timeAT
        this.subject = subject
        this.isStart = isStart
        this.cclass = cclass
        this.totalStudent = totalStudent
        this.attendStudent = attendStudent
        this.room = room
        this.scheduleId = scheduleId
    }
}