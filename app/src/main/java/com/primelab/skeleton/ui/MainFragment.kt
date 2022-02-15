package com.primelab.skeleton.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.primelab.common.extensions.observeResultFlow
import com.primelab.common.logger.Log
import com.primelab.common.session.UserSession
import com.primelab.common.session.UserToken
import com.primelab.common.ui.BaseFragment
import com.primelab.skeleton.R
import com.primelab.skeleton.databinding.FragmentMainBinding
import com.primelab.skeleton.viewmodels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Created by m.imran
 * Senior Software Engineer at
 * PrimeLab.io on 09/02/2022.
 */
@AndroidEntryPoint
class MainFragment : BaseFragment(R.layout.fragment_main) {
    private lateinit var binding: FragmentMainBinding
    private val userViewModel: UserViewModel by activityViewModels()

    private val tokenObserver: (t: UserToken?) -> Unit = {
        Log.d(">>>MainActivity", "Token Is $it")
        if (it != null && it.token.isNotEmpty()) {

        } else {
            //requireActivity().finish()
        }
    }

    @Inject
    lateinit var userSession: UserSession
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        binding.clickMe.setOnClickListener {
            login()
        }
        //getAllUsers()
        userViewModel.userSession.token.observe(viewLifecycleOwner, tokenObserver)
    }

    private fun getAllUsers() {
        observeResultFlow(
            userViewModel.getAllUser(),
            errorHandler = {
                Log.d(">>>Test", "Error is : $it")
            },
            successHandler = {
                Log.d(">>>Test", "Data is : $it")
            }
        )
    }

    private fun login() {
        Log.d(">>>Test", "Token ${userSession.token}")
        val params = hashMapOf<String, Any>()
        observeResultFlow(
            userViewModel.loginUser(params),
            loadingHandler = {
                Log.d(">>>Test", "Loading")
                Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
            },
            successHandler = {
                Log.d(">>>Test", "Success")
            },
            errorHandler = {
                Log.d(">>>Test", "Error ${it.message}")
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            }
        )
    }
}