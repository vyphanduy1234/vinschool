package com.example.vinschoolattendance

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.vinschoolattendance.R
import com.example.vinschoolattendance.viewmodels.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.layout_login.*
import kotlinx.android.synthetic.main.layout_register.*

class LoginActivity : AppCompatActivity() {
    private val TAG = "LoginActivity"
    private var loginViewModel: LoginViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initEvent()
    }

    fun initEvent(){
        //event
        tv_register.setOnClickListener{
            layout_login.visibility = View.GONE
            layout_register.visibility = View.VISIBLE
        }
        //event
        tv_back_to_login.setOnClickListener {
            layout_login.visibility = View.VISIBLE
            layout_register.visibility = View.GONE
        }
        //login event
        btn_login.setOnClickListener {
            if(tv_email.text.toString() == "1"){
                val intent = Intent(this@LoginActivity,TeacherActivity::class.java)
                startActivity(intent)
            }else{
                val intent = Intent(this@LoginActivity,StudentActivity::class.java)
                startActivity(intent)
            }

        }
        //event
        btn_register.setOnClickListener {

        }

    }
}