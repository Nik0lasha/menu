package com.example.whattocook.base

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity(private var container: Int) : AppCompatActivity(){

    val fragmentRouter: FragmentRouter by lazy {
        FragmentRouter(
            container,
            supportFragmentManager
        )
    }
}

