package com.example.vinschoolattendance.database

import androidx.room.Dao
import androidx.room.Query
import com.appsnipp.creativelogindesigns.model.StudentSchedule
import com.example.vinschoolattendance.models.entities.StudentProfile
import com.example.vinschoolattendance.models.entities.TeacherProfile
import com.example.vinschoolattendance.models.entities.TeacherSchedule

@Dao
interface DbDao {
    @Query("select * from StudentSchedule")
    fun getStudentSchedule(): List<StudentSchedule>

    @Query("select * from StudentProfile")
    fun getStudentProfile(): List<StudentProfile>

    @Query("select * from TeacherSchedule")
    fun getTeacherSchedule(): List<TeacherSchedule>

    @Query("select * from TeacherProfile")
    fun getTeacherProfile(): List<TeacherProfile>
}