package com.example.whattocook

import android.os.Bundle
import androidx.appcompat.widget.SwitchCompat
import com.example.whattocook.base.BaseActivity
import com.example.whattocook.base.RestartInterface
import com.example.whattocook.presentation.fragments.SelectionFragment

class MainActivity : BaseActivity(R.id.container), RestartInterface {
    companion object{
        var isConnect: Int = -1
    }
    private var switchCompat: SwitchCompat? = null
    private lateinit var saveData: SaveData


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            fragmentRouter.openSplashFragment()
        }
    override fun onBackPressed() {
        when (supportFragmentManager.backStackEntryCount) {
            0 -> super.onBackPressed()
            else -> supportFragmentManager.popBackStack()
        }
    }
    override fun restartApplication() {
        SelectionFragment.sharedPreferences?.edit()?.clear()?.apply()
        recreate()
    }
}
