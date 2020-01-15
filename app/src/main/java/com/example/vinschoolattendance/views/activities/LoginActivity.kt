package com.example.vinschoolattendance

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.vinschoolattendance.models.pojos.LoginRequest
import com.example.vinschoolattendance.network.Network
import com.example.vinschoolattendance.utils.Loader
import com.example.vinschoolattendance.viewmodels.LoginViewModel
import com.example.vinschoolattendance.views.base.IBaseView
import com.roger.catloadinglibrary.CatLoadingView
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.layout_login.*
import kotlinx.android.synthetic.main.layout_register.*
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity(), IBaseView {

    private val TAG = "LoginActivity"

    lateinit var mViewModel: LoginViewModel

    private var loginViewModel: LoginViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setUpViewModel()
        initEvent()
    }

    override fun setUpViewModel() {
        mViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        //nếu là học sinh đăng nhập thì chuyển sang màn học sinh
        mViewModel.isStudentLogin().observe(this, Observer {
            Loader.hideLoader(fragmentManager = supportFragmentManager)
            val intent = Intent(this@LoginActivity, StudentActivity::class.java)
            startActivity(intent)
        })

        //nếu là giáo viên đăng nhập thì chuyển sang màn giáo viên
        mViewModel.isTeacherLogin().observe(this, Observer {
            Loader.hideLoader(fragmentManager = supportFragmentManager)
            val intent = Intent(this@LoginActivity, TeacherActivity::class.java)
            startActivity(intent)
        })

        // lắng nghe sự kiện đăng nhập thành công hay thất bại
        mViewModel.getInternetStatus().observe(this, Observer {
            when (it) {
                Network.NETWORK_CONNECT_ERROR -> {
                    Loader.hideLoader(supportFragmentManager)
                    Toast.makeText(this,"Login time out!!",Toast.LENGTH_LONG).show()
                }
            }
        })

            mViewModel.getRecordStatus().observe(this, Observer {
                when (it) {
                    Network.NOT_FOUND -> {
                        Loader.hideLoader(supportFragmentManager)
                        Toast.makeText(this,"Wrong username or password!",Toast.LENGTH_LONG).show()
                    }
                }
        })
    }

    override fun initEvent() {
        //event
        //login event
        btn_login.setOnClickListener {
            if (!tv_email.text.toString().equals("") && !tv_password.text.toString().equals("")
            ) {
                Loader.showLoader(fragmentManager = supportFragmentManager)
                mViewModel.login(
                    LoginRequest(
                        tv_email.text.toString(),
                        tv_password.text.toString()
                    )
                )
            }
        }
    }

    fun isEmailValid(email: String): Boolean {
        return Pattern.compile(
            "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
                    + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                    + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
                    + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
        ).matcher(email).matches()
    }
}