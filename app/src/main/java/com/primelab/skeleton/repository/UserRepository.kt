package com.primelab.skeleton.repository

import com.primelab.common.repository.BaseRepository
import com.primelab.skeleton.database.daos.UserDao
import com.primelab.skeleton.networks.UserApi
import javax.inject.Inject

/**
 * Created by m.imran
 * Senior Software Engineer at
 * PrimeLab.io on 09/02/2022.
 */
class UserRepository @Inject constructor(
    private val api: UserApi,
    private val userDao: UserDao
) : BaseRepository() {
    suspend fun login(params: HashMap<String, Any>) =
        getNetWorkResponse(request = { api.login(params) })

    suspend fun getAllUser() =
        getDbResponse(request = { userDao.getAll() })
}