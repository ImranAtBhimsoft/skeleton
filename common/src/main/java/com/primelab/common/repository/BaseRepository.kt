package com.primelab.common.repository

import com.google.gson.Gson
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
abstract class BaseRepository {
    suspend fun <T> getNetWorkResponse(
        request: suspend () -> Response<T>,
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ): Resource<T> {

        return withContext(dispatcher) {
            try {
                val result = request.invoke()
                if (result.isSuccessful) {
                    Resource.Success(result.body())
                } else {
                    Resource.Error(parseError(HttpException(result)))
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
}