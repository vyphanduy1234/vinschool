package com.example.vinschoolattendance.views.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.example.vinschoolattendance.R
import com.example.vinschoolattendance.databinding.FragmentStudentProfileBinding
import com.example.vinschoolattendance.utils.UserAuthen
import com.example.vinschoolattendance.viewmodels.HelperViewModel
import com.example.vinschoolattendance.views.base.IBaseView

/**
 * A simple [Fragment] subclass.
 */
class StudentProfileFragment : Fragment(), IBaseView {

    lateinit var mView: View

    lateinit var mViewModel: HelperViewModel

    lateinit var mBinding: FragmentStudentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_student_profile, container, false)
        mBinding = DataBindingUtil.setContentView(activity!!,R.layout.fragment_student_profile)

        setUpViewModel()
        return mView
    }

    override fun initEvent() {

    }

    override fun setUpViewModel() {
        mViewModel = ViewModelProviders.of(this).get(HelperViewModel::class.java)
        mViewModel.loadStudentProfile(UserAuthen.ID)

        mViewModel.getStudentProfile().observe(this, Observer {
            mBinding.viewmodel = it
        })

    }

}
