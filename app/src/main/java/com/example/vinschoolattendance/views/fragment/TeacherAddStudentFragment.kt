package com.example.vinschoolattendance.views.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.example.vinschoolattendance.R
import com.example.vinschoolattendance.models.pojos.ClassResponse
import com.example.vinschoolattendance.models.pojos.StudentRequest
import com.example.vinschoolattendance.utils.Loader
import com.example.vinschoolattendance.viewmodels.HelperViewModel
import com.example.vinschoolattendance.views.base.IBaseView
import kotlinx.android.synthetic.main.layout_register.*
import kotlinx.android.synthetic.main.layout_register.view.*

/**
 * A simple [Fragment] subclass.
 */
class TeacherAddStudentFragment : Fragment(), IBaseView {

    lateinit var mClassAdapter: ArrayAdapter<ClassResponse>

    lateinit var mView: View

    lateinit var mViewModel: HelperViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_teacher_add_student, container, false)
        setUpViewModel()
        initEvent()
        return mView
    }

    override fun initEvent() {
        mView.btn_add.setOnClickListener {
            val firstName = mView.edt_first_name.text.toString()
            val lastName = mView.edt_last_name.text.toString()
            val email = mView.edt_email.text.toString()
            val account = mView.edt_account.text.toString()
            val password = mView.edt_password.text.toString()
            val cclass = (spn_class.selectedItem as ClassResponse).id

            Loader.showLoader(activity!!.supportFragmentManager)

            val student = StudentRequest(
                firstName = firstName, lastName = lastName
                , email = email, account = account, password = password, classId = cclass
            )
            mViewModel.addNewStudent(student)
        }
    }

    override fun setUpViewModel() {
        mViewModel = ViewModelProviders.of(this).get(HelperViewModel::class.java)
        mViewModel.loadClass()
        mViewModel.getClass().observe(this, Observer {
            mClassAdapter = ArrayAdapter<ClassResponse>(
                context
                , android.R.layout.simple_spinner_dropdown_item
                , it
            )
            mView.spn_class.adapter = mClassAdapter
            mView.spn_class.setSelection(0)
        })

        mViewModel.getAddStudentStatus().observe(this, Observer {
            Loader.hideLoader(activity!!.supportFragmentManager)
            if (it == HelperViewModel.ADD_SUCCESS) {
                Toast.makeText(context, "Them moi thanh cong", Toast.LENGTH_LONG)
            } else {
                Toast.makeText(context, "Them moi that bai", Toast.LENGTH_LONG)
            }
        })
    }
}
