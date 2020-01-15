package com.example.vinschoolattendance

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.vinschoolattendance.views.base.IBaseView
import com.example.vinschoolattendance.views.fragment.StudentProfileFragment
import com.example.vinschoolattendance.views.fragment.StudentScheduleFragment

import kotlinx.android.synthetic.main.activity_student.*

class StudentActivity : AppCompatActivity(), IBaseView {

    private val PROFILE: String = "profile"

    private val SCHEDULE: String = "schedule"

    private var selectedFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student)
        setUpViewPager()
    }

    override fun setUpViewModel(){

    }

    override fun initEvent() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun setUpViewPager() {
        val fragmentManager = supportFragmentManager

        //set up when first navigate to activity
        cn_bottom_menu.setItemSelected(R.id.s_schedule,true)
        setTabNavigation(fragmentManager,SCHEDULE)

        //set event when click on bottom navigation
        cn_bottom_menu.setOnItemSelectedListener { id ->
            when (id) {
                R.id.s_schedule -> {
                    setTabNavigation(fragmentManager,SCHEDULE)
                }
                R.id.s_profile -> {
                    setTabNavigation(fragmentManager,PROFILE)
                }
            }
        }
    }

    private fun setTabNavigation(fragmentManager: FragmentManager,tab: String){

        when(tab){
            PROFILE ->{
                selectedFragment =
                    StudentProfileFragment()
                fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, selectedFragment!!)
                    .commit()
            }
            SCHEDULE ->{
                selectedFragment =
                    StudentScheduleFragment()
                fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, selectedFragment!!)
                    .commit()
            }
        }
    }

}
