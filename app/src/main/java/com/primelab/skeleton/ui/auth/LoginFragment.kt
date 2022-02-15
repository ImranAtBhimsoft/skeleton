package com.primelab.skeleton.ui.auth

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.primelab.common.extensions.viewBinding
import com.primelab.common.logger.Log
import com.primelab.common.ui.BaseFragment
import com.primelab.skeleton.R
import com.primelab.skeleton.databinding.FragmentLoginBinding
import com.primelab.skeleton.viewmodels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by m.imran
 * Senior Software Engineer at
 * PrimeLab.io on 10/02/2022.
 */
@AndroidEntryPoint
class LoginFragment : BaseFragment(R.layout.fragment_login) {

    private val userViewModel: UserViewModel by activityViewModels()
    private val binding by viewBinding(FragmentLoginBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(">>>Login", "${binding.title.text}")
        binding.toOtp.setOnClickListener {
            findNavController().navigate(R.id.toOtpFragment)
        }
        binding.toSignup.setOnClickListener {
            findNavController().navigate(R.id.toSignupFragment)
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().finishAffinity()
                }
            })
    }
}