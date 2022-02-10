package com.primelab.common.repository

/**
 * Created by m.imran
 * Senior Software Engineer at
 * PrimeLab.io on 09/02/2022.
 */
sealed class Resource<T> {
    data class Success<T>(val data: T?) : Resource<T>()

    data class Error<T>(val error: AppError) : Resource<T>()

    data class Loading<T>(val data: T? = null) : Resource<T>()

    data class None<T>(val data: T?=null) : Resource<T>()
}