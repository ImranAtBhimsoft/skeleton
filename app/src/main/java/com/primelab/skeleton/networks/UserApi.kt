package com.primelab.skeleton.networks

import com.primelab.common.session.UserToken
import com.primelab.skeleton.networks.models.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

/**
 * Created by m.imran
 * Senior Software Engineer at
 * PrimeLab.io on 09/02/2022.
 */
interface UserApi {
    @GET("/login")
    suspend fun login(@QueryMap params: HashMap<String, Any>): Response<User>

    @GET("/refreshToken")
    suspend fun refreshToken(@Query("token") params: String): Response<UserToken>
}