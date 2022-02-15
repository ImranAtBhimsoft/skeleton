package com.primelab.skeleton.ui.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.primelab.common.extensions.clearAndNavigate
import com.primelab.common.extensions.viewBinding
import com.primelab.common.session.UserToken
import com.primelab.common.ui.BaseFragment
import com.primelab.skeleton.R
import com.primelab.skeleton.databinding.FragmentOtpBinding
import com.primelab.skeleton.viewmodels.UserViewModel


/**
 * Created by m.imran
 * Senior Software Engineer at
 * PrimeLab.io on 12/02/2022.
 */
class OtpFragment : BaseFragment(R.layout.fragment_otp) {
    private val binding by viewBinding(FragmentOtpBinding::bind)
    private val userViewModel: UserViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.verify.setOnClickListener {
            userViewModel.userSession.setUserToken(UserToken("abc"))
            clearAndNavigate(R.id.toMainFragment)
        }
    }
}