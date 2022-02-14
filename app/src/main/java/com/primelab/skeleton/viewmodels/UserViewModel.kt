package com.primelab.skeleton.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.primelab.common.extensions.resultFlow
import com.primelab.common.session.UserSession
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
    @Inject
    lateinit var userSession: UserSession

    fun loginUser(param: HashMap<String, Any>) = resultFlow {
        repository.login(param)
    }

    fun getAllUser() = resultFlow {
        repository.getAllUser()
    }
}