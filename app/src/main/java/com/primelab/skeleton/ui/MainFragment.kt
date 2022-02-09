package com.primelab.skeleton.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.primelab.common.observeResultFlow
import com.primelab.common.ui.BaseFragment
import com.primelab.skeleton.R
import com.primelab.skeleton.databinding.FragmentMainBinding
import com.primelab.skeleton.viewmodels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by m.imran
 * Senior Software Engineer at
 * PrimeLab.io on 09/02/2022.
 */
@AndroidEntryPoint
class MainFragment : BaseFragment(R.layout.fragment_main) {
    private lateinit var binding: FragmentMainBinding
    private val userViewModel: UserViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        binding.clickMe.setOnClickListener {
            login()
        }
    }

    private fun login() {
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