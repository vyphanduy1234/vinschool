package com.example.vinschoolattendance.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.roger.catloadinglibrary.CatLoadingView

object Loader {

    private var mLoader = CatLoadingView()
    lateinit private var mLoaderFragment: Fragment
    private val TAG = "loader"

    /**
     * hiển thị loader
     * */
    fun showLoader(fragmentManager: FragmentManager) {
        mLoader.show(fragmentManager, TAG)
    }

    /**
     * ẩn loader
     * */
    fun hideLoader(fragmentManager: FragmentManager) {
        fragmentManager?.let{
            mLoaderFragment = it.findFragmentByTag(TAG)!!
            if (mLoaderFragment != null) {
                fragmentManager.beginTransaction().remove(mLoaderFragment).commit()
            }
        }
    }
}