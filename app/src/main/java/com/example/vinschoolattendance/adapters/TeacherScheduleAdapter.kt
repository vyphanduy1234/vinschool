package com.example.vinschoolattendance.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vinschoolattendance.R
import com.example.vinschoolattendance.models.entities.TeacherSchedule
import com.example.vinschoolattendance.views.activities.TeacherEditStudentAttendanceActivity
import com.example.vinschoolattendance.views.activities.TeacherTakeAttendanceActivity
import kotlinx.android.synthetic.main.item_teacher_schedule.view.*

class TeacherScheduleAdapter(var listSchedule: MutableList<TeacherSchedule>, var context: Context) :
    RecyclerView.Adapter<TeacherScheduleAdapter.TeacherScheduleViewHolder>() {

    private val PRE_TIME_HOUR: String = "0"

    inner class TeacherScheduleViewHolder(view: View) : RecyclerView.ViewHolder(view) {

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
        holder.itemView.tv_total_student.text = teacherSchedule.cclass
        holder.itemView.tv_total_student_attend.text = teacherSchedule.cclass
        when (teacherSchedule.isStart) {
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
        holder.itemView.btn_teacher_take_attend.setOnClickListener {
            val intent = Intent(
                context,
                TeacherTakeAttendanceActivity::class.java
            )
            intent.putExtra("schedule_id", teacherSchedule.scheduleId)
            context.startActivity(intent)
        }
        // teacher edit attendance
        holder.itemView.btn_teacher_edit_attend.setOnClickListener {
            val intent = Intent(
                context,
                TeacherEditStudentAttendanceActivity::class.java
            )
            context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherScheduleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var view = inflater.inflate(R.layout.item_teacher_schedule, parent, false)

        return TeacherScheduleViewHolder(view)
    }

    override fun getItemCount() = listSchedule.size
}