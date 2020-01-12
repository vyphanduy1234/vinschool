package com.example.vinschoolattendance.models.entities

import androidx.room.Entity
import com.example.vinschoolattendance.models.base.BaseProfile

@Entity(tableName = "StudentProfile")
class StudentProfile :
    BaseProfile(account = "", avatarLink = "", cclass = "", email = "", name = "",id = "") {
}