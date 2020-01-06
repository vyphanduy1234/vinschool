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
import com.example.vinschoolattendance.models.TeacherSchedule
import com.example.vinschoolattendance.views.TeacherEditStudentAttendance
import com.example.vinschoolattendance.views.TeacherTakeAttendanceActivity

class TeacherScheduleAdapter(var listSchedule: MutableList<TeacherSchedule>,var context: Context)
    : RecyclerView.Adapter<TeacherScheduleAdapter.TeacherScheduleViewHolder>()
{

    inner class TeacherScheduleViewHolder(view: View)
        : RecyclerView.ViewHolder(view){
        var textTimeHour: TextView = view.findViewById(R.id.tv_time_hour)
        var textTimeMinute: TextView = view.findViewById(R.id.tv_time_minute)
        var textTimeAT: TextView = view.findViewById(R.id.tv_time_at)
        var textClass: TextView = view.findViewById(R.id.tv_class)
        var textSubjectRoom: TextView = view.findViewById(R.id.tv_subject_room)
        var textClassTime: TextView = view.findViewById(R.id.tv_time_last)
        var imSubjectStart: ImageView = view.findViewById(R.id.im_attend)
        var imSubjectNotStart: ImageView = view.findViewById(R.id.im_not_attend)
        var btnTake: Button = view.findViewById(R.id.btn_teacher_take_attend)
        var btnEdit: Button = view.findViewById(R.id.btn_teacher_edit_attend)
    }

    override fun onBindViewHolder(holder: TeacherScheduleViewHolder, position: Int) {
        val teacherSchedule = listSchedule[position]

        holder.textTimeHour.text = teacherSchedule.timeHour
        holder.textTimeMinute.text = teacherSchedule.timeMinute
        holder.textTimeAT.text = teacherSchedule.timeAT
        holder.textClass.text = teacherSchedule.cclass
        // teacher take attendance
        holder.btnTake.setOnClickListener {
            val intent = Intent(context,TeacherTakeAttendanceActivity::class.java)
            context.startActivity(intent)
        }
        // teacher edit attendance
        holder.btnEdit.setOnClickListener {
            val intent = Intent(context,TeacherEditStudentAttendance::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherScheduleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var view = inflater.inflate(R.layout.item_teacher_schedule,parent,false)

        return TeacherScheduleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listSchedule.size
    }
}