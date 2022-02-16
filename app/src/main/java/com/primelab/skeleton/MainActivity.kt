package com.primelab.skeleton

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.primelab.common.logger.Log
import com.primelab.common.session.UserSession
import com.primelab.common.session.UserToken
import com.primelab.common.ui.BaseActivity
import com.primelab.skeleton.viewmodels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private lateinit var navigationBarView: BottomNavigationView
    private lateinit var toolBar: MaterialToolbar
    private val userViewModel by viewModels<UserViewModel>()
    @Inject
    lateinit var userSession: UserSession
    private val navController by lazy { (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController }
    private val tokenObserver: (t: UserToken?) -> Unit = {
        Log.d(">>>MainActivity", "Token Is $it")
        if (it != null && it.token.isNotEmpty()) {
            toolBar.visibility = View.VISIBLE
            navigationBarView.visibility = View.VISIBLE
        } else {
            toolBar.visibility = View.GONE
            navigationBarView.visibility = View.GONE
            navController.navigate(R.id.toAuth)
        }
    }
     lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(">>>MainActivity", "onCreate()")
        navigationBarView = findViewById(R.id.bottom_navigation_view)
        toolBar = findViewById(R.id.tool_bar)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        toolBar.setupWithNavController(navController, appBarConfiguration)
        navigationBarView.setupWithNavController(navController)
        //userViewModel.userSession.token.observe(this, tokenObserver)
    }

    override fun onStart() {
        super.onStart()
//        userViewModel.repository.userSession.token.observe(this, tokenObserver)
        userSession.token.observe(this, tokenObserver)
    }

    override fun onStop() {
        super.onStop()
//        userViewModel.repository.userSession.token.removeObserver(tokenObserver)
        userSession.token.removeObserver(tokenObserver)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Log.d(">>>MainActivity", "onBackPressed()")
    }
}