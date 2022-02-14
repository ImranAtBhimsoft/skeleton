package com.primelab.common.repository

import com.google.gson.Gson
import com.primelab.common.session.UserSession
import com.primelab.common.session.UserToken
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.net.UnknownHostException

/**
 * Created by m.imran
 * Senior Software Engineer at
 * PrimeLab.io on 09/02/2022.
 */
abstract class BaseRepository(private val userSession: UserSession) {
    suspend fun <T> getNetWorkResponse(
        request: suspend () -> Response<T>,
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        retry: Boolean = true
    ): Resource<T> {

        return withContext(dispatcher) {
            try {
                val result = request.invoke()
                if (result.isSuccessful) {
                    Resource.Success(result.body())
                } else {
                    val httpException = HttpException(result)
                    if (httpException.code() == 403 && retry) {
                        refreshToken()
                        getNetWorkResponse(request = request, retry = false)
                    } else {
                        Resource.Error(parseError(httpException))
                    }
                }
            } catch (ex: Throwable) {
                var message = ex.localizedMessage.orEmpty()
                if (ex is UnknownHostException) {
                    message = "Internet connection problem. please try again"
                }
                Resource.Error(AppError(message, -1))
            }
        }
    }

    private suspend fun refreshToken() {
        val tokenResult = getRefreshTokenApi()
        val tokenBody = tokenResult?.body()
        userSession.token.postValue(tokenBody)
    }

    suspend fun <T> getDbResponse(
        request: suspend () -> T,
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ): Resource<T> {

        return withContext(dispatcher) {
            try {
                Resource.Success(request.invoke())
            } catch (ex: Throwable) {
                val message = ex.localizedMessage.orEmpty()
                Resource.Error(AppError(message, -1))
            }
        }
    }

    private fun parseError(throwable: HttpException): AppError {
        val response = throwable.response()
        return response?.let {
            val body = it.errorBody()
            Gson().fromJson(body?.string(), AppError::class.java).apply {
                code = if (code == -1) it.code() else code
            }
        } ?: run {
            AppError(throwable.message(), response?.code() ?: -1)
        }
    }

    abstract suspend fun getRefreshTokenApi(): Response<UserToken>?
}