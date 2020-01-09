package com.example.vinschoolattendance.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.appsnipp.creativelogindesigns.model.StudentSchedule
import com.bumptech.glide.Glide
import com.example.vinschoolattendance.R
import com.example.vinschoolattendance.views.activities.StudentTakeAttendanceActivity
import kotlinx.android.synthetic.main.item_student_schedule.view.*

class StudentScheduleAdapter(var listSchedule: MutableList<StudentSchedule>, var context: Context) :
    RecyclerView.Adapter<StudentScheduleAdapter.StudentScheduleViewHolder>() {

    inner class StudentScheduleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentScheduleViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        var view = layoutInflater.inflate(R.layout.item_student_schedule, parent, false)
        return StudentScheduleViewHolder(view)
    }

    override fun getItemCount() = listSchedule.size

    override fun onBindViewHolder(holder: StudentScheduleViewHolder, position: Int) {
        val studentSchedule = listSchedule[position]

        holder.itemView.tv_time_minute.text =
            if (studentSchedule.timeStartHour.toString().length == 1)
                "0" + studentSchedule.timeStartHour.toString()
            else
                studentSchedule.timeStartHour.toString()
        holder.itemView.tv_time_minute.text =
            if (studentSchedule.timeStartMinute.toString().length == 1)
                "0" + studentSchedule.timeStartMinute.toString()
            else
                studentSchedule.timeStartMinute.toString()
        holder.itemView.tv_time_at.text = studentSchedule.timeStartAT
        holder.itemView.tv_subject.text = studentSchedule.subject
        holder.itemView.tv_room.text = "Room: ${studentSchedule.room}"
        holder.itemView.tv_teacher.text = "Teacher: ${studentSchedule.teacher}"
        when (studentSchedule.isAttend) {
            true -> {
                holder.itemView.im_not_attend.setImageResource(R.drawable.na_check)
            }
            false -> {
                holder.itemView.im_attend.setImageResource(R.drawable.na_check)
            }
        }
        holder.itemView.tv_class.text = "Class: " + studentSchedule.cclass
        holder.itemView.btn_student_take_attend.setOnClickListener {
            val intent: Intent = Intent(context, StudentTakeAttendanceActivity::class.java)
            context.startActivity(intent)
        }

        loadImageStudentAttend(holder, studentSchedule)
    }

    private fun loadImageStudentAttend(
        holder: StudentScheduleViewHolder,
        studentSchedule: StudentSchedule
    ) {
        val listImage: MutableList<ImageView> = mutableListOf(
            holder.itemView.im_people_attend_1,
            holder.itemView.im_people_attend_2,
            holder.itemView.im_people_attend_3,
            holder.itemView.im_people_attend_4,
            holder.itemView.im_people_attend_5
        )
        for (i in 0 until 5) {
            if (i < studentSchedule.friendAttends.size) {
                Glide.with(context).load(studentSchedule.friendAttends[i]).into(listImage[i])
            } else {
                listImage[i].setImageResource(R.drawable.na_avartar)
            }
        }
    }
}