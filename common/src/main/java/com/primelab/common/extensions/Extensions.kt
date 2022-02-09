package com.primelab.common

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.primelab.common.repository.Resource
import com.primelab.common.repository.RetrofitError
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Created by m.imran
 * Senior Software Engineer at
 * PrimeLab.io on 09/02/2022.
 */

typealias ErrorHandler = (retroError: RetrofitError) -> Unit
typealias SuccessHandler<T> = (value: T) -> Unit
typealias LoadingHandler = () -> Unit

fun <T> ViewModel.resultFlow(
    firstValue: Resource<T> = Resource.None(),
    callback: suspend () -> Resource<T>
): MutableStateFlow<Resource<T>> = MutableStateFlow(firstValue).apply {
    viewModelScope.launch {
        with(this@resultFlow) {
            tryEmit(Resource.Loading())
            value = callback.invoke()
        }
    }
}

fun <T> LifecycleCoroutineScope.collect(
    flow: StateFlow<Resource<T>>,
    errorHandler: ErrorHandler? = null,
    successHandler: SuccessHandler<T>? = null,
    loadingHandler: LoadingHandler? = null
) {
    launch {
        flow.collect {
            when (it) {
                is Resource.Success -> {
                    it.data?.let { data -> successHandler?.invoke(data) }
                }
                is Resource.Error -> {
                    errorHandler?.invoke(it.error)
                }
                is Resource.Loading -> {
                    loadingHandler?.invoke()
                }
                else -> {
                }
            }
        }
    }
}

fun <T> Fragment.observeResultFlow(
    stateFlow: StateFlow<Resource<T>>,
    loadingHandler: LoadingHandler = { },
    errorHandler: ErrorHandler = { },
    successHandler: SuccessHandler<T>
) {
    lifecycleScope.collect(
        stateFlow,
        successHandler = { result ->
            result?.let {
                successHandler.invoke(it)
            }
        },
        errorHandler = {
            errorHandler.invoke(it)
        },
        loadingHandler = {
            loadingHandler.invoke()
        }
    )
}

