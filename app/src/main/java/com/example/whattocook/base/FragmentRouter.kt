package com.example.whattocook.base

import android_courses.whattocook.presentation.fragments.SignInFragment
import android_courses.whattocook.presentation.fragments.SignUpFragment
import android_courses.whattocook.presentation.fragments.FragmentSetting
import android_courses.whattocook.presentation.fragments.NewsFragment
import android_courses.whattocook.presentation.fragments.SelectionFragment
import android_courses.whattocook.presentation.fragments.SplashScreenFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.whattocook.R

class FragmentRouter(private val containerId: Int, private val fragmentManager: FragmentManager)
    : Router {

    private fun replaceFragment(fragment: Fragment) {
        fragmentManager.beginTransaction()
            .replace(containerId, fragment)
            .commit()
    }

    private fun addFragmentWithAnimation(
        fragment: Fragment,
        anim_open: Int = R.animator.slide_in_left,
        anim_close: Int = R.animator.slide_in_right) {
        fragmentManager.beginTransaction().apply {
            setCustomAnimations(anim_open, anim_close, anim_open, anim_close)
            add(containerId, fragment).addToBackStack(null).commit()
        }
    }

    override fun openLogInFragment() {
        replaceFragment(SignInFragment())
    }

    override fun openSettingsFragment() {
        addFragmentWithAnimation(FragmentSetting(),
                R.animator.slide_out_right,
                R.animator.slide_out_left)
    }

    override fun openSelectionFragment() {
        addFragmentWithAnimation(SelectionFragment())
    }

    override fun openSignUpFragment() {
        addFragmentWithAnimation(SignUpFragment())
    }
    override fun openSplashFragment() {
        replaceFragment(SplashScreenFragment())
    }

    override fun openNewsFragment() {
        replaceFragment(NewsFragment())
    }
}