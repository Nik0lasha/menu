package com.example.whattocook.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.whattocook.R
import com.example.whattocook.base.BaseActivity
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class SplashScreenFragment : Fragment(), CoroutineScope {

    companion object {
        private const val TIME: Long = 2000
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + Job()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        launch {
            delay(TIME)
            withContext(Dispatchers.Main) {
                (requireActivity() as BaseActivity).fragmentRouter.openLogInFragment()
            }
        }
    }
}