package com.primelab.common.session

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
    var token: MutableLiveData<UserToken?> = MutableLiveData()
        set(value) {
            field = value
            save()
        }

    init {
        CoroutineScope(Dispatchers.IO).launch {
            token.postValue(userTokenDao.getToken())
        }
    }

    private fun save() {
        CoroutineScope(Dispatchers.IO).launch {
            token.value?.let {
                userTokenDao.save(it)
                Log.d(">>>Token", "saved")
            } ?: run {
                Log.d(">>>Token", "deleting")
                userTokenDao.delete()
            }
        }
    }
}