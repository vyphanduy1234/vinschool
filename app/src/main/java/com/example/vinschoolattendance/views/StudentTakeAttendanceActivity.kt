package com.example.vinschoolattendance.views

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.Camera
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView

class StudentTakeAttendanceActivity : AppCompatActivity(), ZXingScannerView.ResultHandler {

    private var scannerView: ZXingScannerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        scannerView = ZXingScannerView(this)
        setContentView(scannerView)
        val currentApiVersion = Build.VERSION.SDK_INT
        if (currentApiVersion >= Build.VERSION_CODES.M) {
            if (checkPermission()) {
                Toast.makeText(
                    getApplicationContext(),
                    "Permission already granted!",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                requestPermission()
            }
        }
    }

    private fun checkPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            getApplicationContext(),
            Manifest.permission.CAMERA
        ) === PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.CAMERA),
            REQUEST_CAMERA
        )
    }

    override fun onResume() {
        super.onResume()
        val currentapiVersion = Build.VERSION.SDK_INT
        if (currentapiVersion >= Build.VERSION_CODES.M) {
            if (checkPermission()) {
                if (scannerView == null) {
                    scannerView = ZXingScannerView(this)
                    setContentView(scannerView)
                }
                scannerView?.setResultHandler(this)
                scannerView?.startCamera()
            } else {
                requestPermission()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        scannerView?.stopCamera()
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CAMERA -> if (grantResults.size > 0) {
                val cameraAccepted =
                    grantResults[0] == PackageManager.PERMISSION_GRANTED
                if (cameraAccepted) {
                    Toast.makeText(
                        getApplicationContext(),
                        "Permission Granted, Now you can access camera",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(
                        getApplicationContext(),
                        "Permission Denied, You cannot access and camera",
                        Toast.LENGTH_LONG
                    ).show()
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                            showMessageOKCancel("You need to allow access to both the permissions",
                                DialogInterface.OnClickListener { dialog, which ->
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        requestPermissions(
                                            arrayOf(Manifest.permission.CAMERA),
                                            REQUEST_CAMERA
                                        )
                                    }
                                })
                            return
                        }
                    }
                }
            }
        }
    }

    private fun showMessageOKCancel(
        message: String,
        okListener: DialogInterface.OnClickListener
    ) {
        android.app.AlertDialog.Builder(this)
            .setMessage(message)
            .setPositiveButton("OK", okListener)
            .setNegativeButton("Cancel", null)
            .create()
            .show()
    }

    override fun handleResult(result: Result) {
        val myResult = result.text
        Log.d("QRCodeScanner", result.text)
        Log.d("QRCodeScanner", result.barcodeFormat.toString())
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Scan Result")
        builder.setPositiveButton("OK",
            DialogInterface.OnClickListener { dialog, which -> scannerView?.resumeCameraPreview(this) })
        builder.setNeutralButton("Visit",
            DialogInterface.OnClickListener { dialog, which ->
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(myResult))
                startActivity(browserIntent)
            })
        builder.setMessage(result.text)
        val alert1: AlertDialog = builder.create()
        alert1.show()
    }

    companion object {
        private const val REQUEST_CAMERA = 1
        private const val camId = Camera.CameraInfo.CAMERA_FACING_BACK
    }
}
