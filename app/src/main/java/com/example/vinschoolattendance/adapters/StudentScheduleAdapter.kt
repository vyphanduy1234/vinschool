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
import com.appsnipp.creativelogindesigns.model.StudentSchedule
import com.example.vinschoolattendance.R
import com.example.vinschoolattendance.views.TeacherTakeAttendanceActivity

class StudentScheduleAdapter(var listSchedule: MutableList<StudentSchedule>,var context: Context)
    : RecyclerView.Adapter<StudentScheduleAdapter.StudentScheduleViewHolder>() {

    inner class StudentScheduleViewHolder(view: View)
        :RecyclerView.ViewHolder(view){
        var textTimeHour: TextView = view.findViewById(R.id.tv_time_hour)
        var textTimeMinute: TextView = view.findViewById(R.id.tv_time_minute)
        var textTimeAT: TextView = view.findViewById(R.id.tv_time_at)
        var textSubject: TextView = view.findViewById(R.id.tv_subject)
        var imAttend: ImageView = view.findViewById(R.id.im_attend)
        var imNotAttend: ImageView = view.findViewById(R.id.im_not_attend)
        var btnTake: Button = view.findViewById(R.id.btn_student_take_attend)
        //var textSubjectTime: TextView = view.findViewById(R.id.tv_s)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentScheduleViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        var view = layoutInflater.inflate(R.layout.layout_student_schedule,parent,false)
        return StudentScheduleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listSchedule.size
    }

    override fun onBindViewHolder(holder: StudentScheduleViewHolder, position: Int) {
        val studentSchedule = listSchedule[position]

        holder.textTimeHour.text = studentSchedule.timeHour
        holder.textTimeMinute.text = studentSchedule.timeMinute
        holder.textTimeAT.text = studentSchedule.timeAT
        holder.textSubject.text = studentSchedule.subject
        holder.btnTake.setOnClickListener {
            val intent: Intent = Intent(context,TeacherTakeAttendanceActivity::class.java)
            context.startActivity(intent)
        }

    }
}