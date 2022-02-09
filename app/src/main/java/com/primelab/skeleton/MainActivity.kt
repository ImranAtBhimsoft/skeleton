package com.primelab.skeleton

import android.os.Bundle
import com.primelab.common.ui.BaseActivity
import com.primelab.skeleton.ui.MainFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager
            .beginTransaction()
            .replace(android.R.id.content, MainFragment())
            .commit()
    }
}