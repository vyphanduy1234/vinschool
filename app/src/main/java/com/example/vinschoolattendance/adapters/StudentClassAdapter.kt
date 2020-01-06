package com.example.vinschoolattendance.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vinschoolattendance.R
import com.example.vinschoolattendance.models.models.StudentOfClass

class StudentClassAdapter(var listStudentClass: MutableList<StudentOfClass>)
    : RecyclerView.Adapter<StudentClassAdapter.StudentClassViewHolder>() {

    inner class StudentClassViewHolder(view: View):RecyclerView.ViewHolder(view){
        var txtName: TextView = view.findViewById(R.id.tv_name)
        var imgAvatar: ImageView = view.findViewById(R.id.img_avatar)
        var imgAttend: ImageView = view.findViewById(R.id.img_attend)
        var imgNotAttend: ImageView = view.findViewById(R.id.img_not_attend)
    }

    override fun onBindViewHolder(holder: StudentClassViewHolder, position: Int) {
        val studentClass = listStudentClass[position]
        holder.imgAvatar.setImageResource(studentClass.avatarSource)
        holder.txtName.text = studentClass.name

        studentClass.isAttend?.let{
            when(it){
                true ->{
                    holder.imgAttend.setImageResource(R.drawable.v_tick)
                    holder.imgNotAttend.setImageResource(R.drawable.na_check)
                }
                false ->{
                    holder.imgNotAttend.setImageResource(R.drawable.x_tick)
                    holder.imgAttend.setImageResource(R.drawable.na_check)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return listStudentClass.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentClassViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var view = inflater.inflate(R.layout.item_student_class,parent,false)

        return StudentClassViewHolder(view)
    }
}