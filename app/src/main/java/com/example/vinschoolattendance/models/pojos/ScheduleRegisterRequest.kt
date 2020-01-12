package com.example.vinschoolattendance.models.pojos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ScheduleRegisterRequest {

    @SerializedName("categoryId")
    @Expose
    var categoryId: Int

    @SerializedName("classId")
    @Expose
    var classId: Int

    @SerializedName("date")
    @Expose
    var date: String

    @SerializedName("roomId")
    @Expose
    var roomId: Int

    @SerializedName("subTrainerId")
    @Expose
    var subTrainerId: Int

    @SerializedName("subjectId")
    @Expose
    var subjectId: Int

    @SerializedName("termId")
    @Expose
    var termId: Int

    constructor(
        categoryId: Int,
        classId: Int,
        date: String,
        roomId: Int,
        subTrainerId: Int,
        subjectId: Int,
        termId: Int
    ) {
        this.categoryId = categoryId
        this.classId = classId
        this.date = date
        this.roomId = roomId
        this.subTrainerId = subTrainerId
        this.subjectId = subjectId
        this.termId = termId
    }
}