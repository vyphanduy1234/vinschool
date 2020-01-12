package com.example.vinschoolattendance.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vinschoolattendance.R
import com.example.vinschoolattendance.models.entities.TeacherSchedule
import com.example.vinschoolattendance.utils.ScheduleStatus
import kotlinx.android.synthetic.main.item_teacher_schedule.view.*
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

class TeacherScheduleAdapter(
    var listSchedule: MutableList<TeacherSchedule>
    , var context: Context, var teacherListener: TeacherScheduleListener
) :
    RecyclerView.Adapter<TeacherScheduleAdapter.TeacherScheduleViewHolder>() {

    private val PRE_TIME_HOUR: String = "0"

    inner class TeacherScheduleViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    interface TeacherScheduleListener {
        fun onEditClassAttendance(scheduleId: Int)
        fun onTakeClassAttendance(scheduleId: Int)
    }

    override fun onBindViewHolder(holder: TeacherScheduleViewHolder, position: Int) {
        val teacherSchedule = listSchedule[position]

        holder.itemView.tv_time_hour.text = if (teacherSchedule.timeHour.toString().length == 1)
            PRE_TIME_HOUR + teacherSchedule.timeHour.toString()
        else
            teacherSchedule.timeHour.toString()
        holder.itemView.tv_time_minute.text = if (teacherSchedule.timeMinute.toString().length == 1)
            PRE_TIME_HOUR + teacherSchedule.timeMinute.toString()
        else
            teacherSchedule.timeMinute.toString()
        holder.itemView.tv_time_at.text = teacherSchedule.timeAT
        holder.itemView.tv_class.text = teacherSchedule.cclass
        holder.itemView.tv_room.text = teacherSchedule.room
        holder.itemView.tv_subject.text = teacherSchedule.subject
        holder.itemView.tv_total_student.text = "Total: ${teacherSchedule.totalStudent}"
        holder.itemView.tv_total_student_attend.text = "Attend: ${teacherSchedule.attendStudent}"
        when (ScheduleStatus.isStarted(teacherSchedule.timeStart,teacherSchedule.date)) {
            true -> {
                holder.itemView.im_not_start.setImageResource(R.drawable.na_check)
                holder.itemView.im_start.setImageResource(R.drawable.v_tick)
            }
            false -> {
                holder.itemView.im_not_start.setImageResource(R.drawable.x_tick)
                holder.itemView.im_start.setImageResource(R.drawable.na_check)
            }
        }

        // teacher take attendance
        if(ScheduleStatus.canTakeAttend(teacherSchedule.timeStart,teacherSchedule.date)){
            holder.itemView.btn_teacher_take_attend.setOnClickListener {
                teacherListener.onTakeClassAttendance(teacherSchedule.scheduleId)
            }
        }else{
            holder.itemView.btn_teacher_take_attend.text = "Started"
            holder.itemView.btn_teacher_take_attend.isEnabled = false
        }
        // teacher edit attendance
        holder.itemView.btn_teacher_edit_attend.setOnClickListener {
            teacherListener.onEditClassAttendance(teacherSchedule.scheduleId)
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherScheduleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var view = inflater.inflate(R.layout.item_teacher_schedule, parent, false)

        return TeacherScheduleViewHolder(view)
    }

    override fun getItemCount() = listSchedule.size
}