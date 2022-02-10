package com.primelab.common.extensions

import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.primelab.common.repository.Resource
import com.primelab.common.repository.AppError
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Created by m.imran
 * Senior Software Engineer at
 * PrimeLab.io on 09/02/2022.
 */

typealias ErrorHandler = (retroError: AppError) -> Unit
typealias SuccessHandler<T> = (value: T) -> Unit
typealias LoadingHandler = () -> Unit

/**
 * resultFlow takes suspended callback and invoke it on
 * view model scope by returning a mutable state flow.
 */
fun <T> ViewModel.resultFlow(
    firstValue: Resource<T> = Resource.None(),
    callback: suspend () -> Resource<T>
): MutableStateFlow<Resource<T>> = MutableStateFlow(firstValue).apply {
    viewModelScope.launch {
        tryEmit(Resource.Loading())
        value = callback.invoke()
    }
}

/**
 * Collects flow results on coroutine scope
 */
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

/**
 * Observed Network call's flows
 * on Fragment
 */
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

/**
 * Make Text as Clickable Link
 * e.g Terms & Services
 */
fun AppCompatTextView.makeLinks(vararg links: Pair<String, View.OnClickListener>) {
    val spannableString = SpannableString(this.text)
    var startIndexOfLink = -1
    for (link in links) {
        val clickableSpan = object : ClickableSpan() {
            override fun updateDrawState(textPaint: TextPaint) {
                textPaint.color = textPaint.linkColor
                textPaint.isUnderlineText = true
            }

            override fun onClick(view: View) {
                Selection.setSelection((view as TextView).text as Spannable, 0)
                view.invalidate()
                link.second.onClick(view)
            }
        }
        startIndexOfLink = this.text.toString().indexOf(link.first, startIndexOfLink + 1)
        spannableString.setSpan(
            clickableSpan, startIndexOfLink, startIndexOfLink + link.first.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
    this.movementMethod =
        LinkMovementMethod.getInstance()
    this.setText(spannableString, TextView.BufferType.SPANNABLE)
}
