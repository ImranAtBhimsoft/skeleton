package com.primelab.skeleton.networks


import com.primelab.common.logger.Log
import com.primelab.common.session.UserSession
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by m.imran
 * Senior Software Engineer at
 * PrimeLab.io on 10/02/2022.
 */
class AuthInterceptor constructor(private val userSession: UserSession) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        Log.d(">>>>AuthInter", "AuthInterceptor->intercept()")
        userSession.token.value?.let {
            Log.d(">>>>AuthInter", "Token is ${it.token}")
        }
        return chain.proceed(chain.request())
    }
}