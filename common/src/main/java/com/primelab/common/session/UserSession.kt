package com.primelab.common.session

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.primelab.common.database.dao.UserTokenDao
import com.primelab.common.logger.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by m.imran
 * Senior Software Engineer at
 * PrimeLab.io on 10/02/2022.
 */
class UserSession constructor(private val userTokenDao: UserTokenDao) {
    private val _token: MutableLiveData<UserToken?> = MutableLiveData()
    val token: LiveData<UserToken?> = _token

    fun setUserToken(token: UserToken?) {
        _token.value = token
        save()
    }


    init {
        CoroutineScope(Dispatchers.IO).launch {
            _token.postValue(userTokenDao.getToken())
        }
    }

    private fun save() {
        CoroutineScope(Dispatchers.IO).launch {
            token.value?.let {
                userTokenDao.save(it)
                Log.d(">>>Token", "saved $it")
            } ?: run {
                userTokenDao.delete()
            }
        }
    }
}