package com.primelab.skeleton.repository

import com.primelab.common.repository.BaseRepository
import com.primelab.common.session.UserSession
import com.primelab.common.session.UserToken
import com.primelab.skeleton.networks.UserApi
import retrofit2.Response

/**
 * Created by m.imran
 * Senior Software Engineer at
 * PrimeLab.io on 09/02/2022.
 */
abstract class AppBaseRepository constructor(
    private val api: UserApi,
    private val userSession: UserSession
) : BaseRepository(userSession) {
    override suspend fun getRefreshTokenApi(): Response<UserToken>? {
        return userSession.token.value?.let { api.refreshToken(it.token) }
    }
}