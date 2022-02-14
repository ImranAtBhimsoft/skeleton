package com.primelab.skeleton.ui

import android.os.Bundle
import android.view.View
import com.primelab.common.extensions.viewBinding
import com.primelab.common.logger.Log
import com.primelab.common.ui.BaseFragment
import com.primelab.skeleton.R
import com.primelab.skeleton.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by m.imran
 * Senior Software Engineer at
 * PrimeLab.io on 10/02/2022.
 */
@AndroidEntryPoint
class ProfileFragment : BaseFragment(R.layout.fragment_profile) {
    private val binding by viewBinding(FragmentProfileBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(">>>Login", "${binding.title.text}")
    }
}