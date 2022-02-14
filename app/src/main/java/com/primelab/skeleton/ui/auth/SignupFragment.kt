package com.primelab.skeleton.ui.auth

import android.os.Bundle
import android.view.View
import com.primelab.common.extensions.viewBinding
import com.primelab.common.logger.Log
import com.primelab.common.ui.BaseFragment
import com.primelab.skeleton.R
import com.primelab.skeleton.databinding.FragmentSignupBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by m.imran
 * Senior Software Engineer at
 * PrimeLab.io on 10/02/2022.
 */
@AndroidEntryPoint
class SignupFragment : BaseFragment(R.layout.fragment_signup) {
    private val binding by viewBinding(FragmentSignupBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(">>>SignUp", "${binding.title.text}")
    }
}