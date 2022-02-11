package com.primelab.skeleton

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.primelab.common.ui.BaseActivity
import com.primelab.skeleton.ui.MainFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(R.layout.activity_main)
        supportFragmentManager
            .beginTransaction()
            .replace(android.R.id.content, MainFragment())
            .commit()
    }
}