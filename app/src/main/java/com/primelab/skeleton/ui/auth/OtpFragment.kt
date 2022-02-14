package com.primelab.skeleton.ui.auth

import android.os.Bundle
import android.view.View
import com.primelab.common.extensions.viewBinding
import com.primelab.common.ui.BaseFragment
import com.primelab.skeleton.R
import com.primelab.skeleton.databinding.FragmentOtpBinding

/**
 * Created by m.imran
 * Senior Software Engineer at
 * PrimeLab.io on 12/02/2022.
 */
class OtpFragment : BaseFragment(R.layout.fragment_otp) {
    private val binding by viewBinding(FragmentOtpBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.title.setOnClickListener {

        }
    }
}