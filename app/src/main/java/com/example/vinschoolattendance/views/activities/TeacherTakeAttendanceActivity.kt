package com.example.vinschoolattendance.views.activities

import android.content.Intent
import android.graphics.Bitmap
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import com.example.vinschoolattendance.R
import com.example.vinschoolattendance.views.base.IBaseView
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import com.journeyapps.barcodescanner.BarcodeEncoder
import kotlinx.android.synthetic.main.activity_teacher_take_attendace.*

class TeacherTakeAttendanceActivity : AppCompatActivity(), IBaseView {

    val TAG: String = "MainActivity"
    val player: MediaPlayer = MediaPlayer()
    var scheduleID: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_take_attendace)

        scheduleID = intent.getIntExtra("schedule_id",-1)
        initEvent()
        setUpViewModel()
    }

    override fun initEvent() {
        btn_take.setOnClickListener() { view ->
            run {
                tv_time_notify.text = "QR will be shown in"
                btn_take.isEnabled = false
                tv_timer.visibility = View.VISIBLE
                val takeAttendanceTimmer = object : CountDownTimer(50000, 1000) {
                    override fun onTick(p0: Long) {
                        val time = p0 / 1000
                        tv_timer.text = time.toString()
                        tv_timer.text = time.toString()
                    }

                    override fun onFinish() {
                        tv_time_notify.text = "Time over"
                        btn_check_attend.isEnabled = true
                        img_qr.visibility = View.INVISIBLE
                    }
                }

                val startTimer = object : CountDownTimer(10000, 1000) {
                    override fun onTick(p0: Long) {
                        val time = p0 / 1000
                        tv_timer.text = time.toString()
                    }

                    override fun onFinish() {
                        scheduleID?.let {
                            var multiFomat: MultiFormatWriter = MultiFormatWriter()
                            var bitMatrix: BitMatrix = multiFomat.encode(
                                "$scheduleID",
                                BarcodeFormat.QR_CODE,
                                img_qr.width,
                                img_qr.height
                            )
                            val barcodeEncoder: BarcodeEncoder = BarcodeEncoder()
                            var bitmap: Bitmap = barcodeEncoder.createBitmap(bitMatrix)
                            img_qr.setImageBitmap(bitmap)
                        }
                        tv_time_notify.text = "Time to scan"
                        takeAttendanceTimmer.start()
                    }
                }
                startTimer.start()

            }
        }
        btn_check_attend.setOnClickListener {
            val intent = Intent(
                this,
                ClassAttendanceActivity::class.java
            )
            startActivity(intent)
        }
    }

    override fun setUpViewModel() {
        Log.d("","")
    }
}
