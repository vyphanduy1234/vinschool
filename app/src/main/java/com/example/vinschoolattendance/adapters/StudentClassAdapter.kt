package com.example.vinschoolattendance.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vinschoolattendance.R
import com.example.vinschoolattendance.models.entities.StudentOfClass
import kotlinx.android.synthetic.main.item_student_class.view.*

class StudentClassAdapter(
    var listStudentClass: MutableList<StudentOfClass>,
    var attendanceChangeListener: onAttendanceChange
) : RecyclerView.Adapter<StudentClassAdapter.StudentClassViewHolder>() {

    private val CAN_CHANGE_STATUS = 1
    private val CANNOT_CHANGE_STATUS = 2

    inner class StudentClassViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    }

    interface onAttendanceChange {
        fun changeToAttend(sid: Int, scheduleId: Int)
        fun changeToNotAttend(sid: Int, scheduleId: Int)
    }

    override fun onBindViewHolder(holder: StudentClassViewHolder, position: Int) {
        val studentClass = listStudentClass[position]
        holder.itemView.img_avatar.setImageResource(studentClass.avatarSource)
        holder.itemView.tv_name.text = studentClass.name

        studentClass.isAttend?.let {
            when (it) {
                true -> {
                    holder.itemView.img_attend.setImageResource(R.drawable.v_tick)
                    holder.itemView.img_not_attend.setImageResource(R.drawable.na_check)
                    holder.itemView.img_not_attend.setTag(CAN_CHANGE_STATUS)
                }
                false -> {
                    holder.itemView.img_not_attend.setImageResource(R.drawable.x_tick)
                    holder.itemView.img_attend.setImageResource(R.drawable.na_check)
                    holder.itemView.img_attend.setTag(CAN_CHANGE_STATUS)
                }
            }
            holder.itemView.img_attend.let {
                it.setOnClickListener {
                    val im = it as ImageView
                    if (im.tag == CAN_CHANGE_STATUS) {
                        im.setImageResource(R.drawable.v_tick)
                        im.setTag(CANNOT_CHANGE_STATUS)
                        holder.itemView.img_not_attend.setImageResource(R.drawable.na_check)
                        holder.itemView.img_not_attend.setTag(CAN_CHANGE_STATUS)

                        attendanceChangeListener.changeToAttend(
                            studentClass.id,
                            studentClass.scheduleId
                        )
                    }
                }
            }

            holder.itemView.img_not_attend.let {
                it.setOnClickListener {
                    val im = it as ImageView
                    if (im.tag == CAN_CHANGE_STATUS) {
                        im.setImageResource(R.drawable.x_tick)
                        im.setTag(CANNOT_CHANGE_STATUS)
                        holder.itemView.img_attend.setImageResource(R.drawable.na_check)
                        holder.itemView.img_attend.setTag(CAN_CHANGE_STATUS)

                        attendanceChangeListener.changeToNotAttend(
                            studentClass.id,
                            studentClass.scheduleId
                        )
                    }
                }
            }

        }
    }

    override fun getItemCount(): Int {
        return listStudentClass.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentClassViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var view = inflater.inflate(R.layout.item_student_class, parent, false)

        return StudentClassViewHolder(view)
    }
}