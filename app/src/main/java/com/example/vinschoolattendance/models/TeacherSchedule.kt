package com.example.vinschoolattendance.models

class TeacherSchedule {
    var timeHour: String
    var timeMinute: String
    var timeAT: String
    var subject: String
    var subjectTime: String
    var isStart: Boolean
    var cclass: String
    var totalStudent: Int
    var attendStudent: Int

    constructor(
        timeHour: String = "07",
        timeMinute: String = "00",
        timeAT: String = "AM",
        subject: String = "Physic",
        subjectTime: String = "1 hour 30 minutes",
        isStart: Boolean = false,
        cclass: String = "12A10",
        totalStudent: Int = 40,
        attendStudent: Int = 38
    ) {
        this.timeHour = timeHour
        this.timeMinute = timeMinute
        this.timeAT = timeAT
        this.subject = subject
        this.subjectTime = subjectTime
        this.isStart = isStart
        this.cclass = cclass
        this.totalStudent = totalStudent
        this.attendStudent = attendStudent
    }
}