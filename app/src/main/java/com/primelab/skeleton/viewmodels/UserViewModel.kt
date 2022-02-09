package com.primelab.skeleton.viewmodels

import androidx.lifecycle.ViewModel
import com.primelab.common.resultFlow
import com.primelab.skeleton.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by m.imran
 * Senior Software Engineer at
 * PrimeLab.io on 09/02/2022.
 */
@HiltViewModel
class UserViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {
    fun loginUser(param: HashMap<String, Any>) = resultFlow {
        repository.login(param)
    }
}