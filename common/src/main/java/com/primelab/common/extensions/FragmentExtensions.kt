package com.primelab.common.extensions

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.activity.OnBackPressedCallback
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController


/**
 * Created by m.imran
 * Senior Software Engineer at
 * PrimeLab.io on 11/02/2022.
 */

fun provideFactory(application: Application, bundle: Bundle) =
    object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return try {
                modelClass.getConstructor(Application::class.java, Bundle::class.java)
                    .newInstance(application, bundle)
            } catch (e: Exception) {
                throw RuntimeException("Cannot create an instance of $modelClass", e)
            }
        }
    }

fun provideFactory(application: Application) =
    object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return try {
                modelClass.getConstructor(Application::class.java)
                    .newInstance(application)
            } catch (e: Exception) {
                throw RuntimeException("Cannot create an instance of $modelClass", e)
            }
        }
    }

fun FragmentActivity.onBackPressedOverride(func: () -> Unit?, viewLifecycleOwner: LifecycleOwner) {
    this.onBackPressedDispatcher.addCallback(
        viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                func()
            }
        })
}

fun Fragment.onBackPressedOverride(func: () -> Unit?) {
    activity?.onBackPressedOverride(func, viewLifecycleOwner)
}

fun Fragment.observeOnDestroy(action: () -> Unit) {
    viewLifecycleOwnerLiveData.observe(viewLifecycleOwner) { viewLifecycleOwner ->
        viewLifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onDestroy(owner: LifecycleOwner) {
                action.invoke()
            }
        })
    }
}

fun Fragment.showKeyboard(view: View?) {
    view?.let {
        val imm = it.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(it, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }
}

private fun Fragment.hideKeyboard(view: View?): Boolean {
    val inputMethodManager =
        view?.context?.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as? InputMethodManager
    return inputMethodManager?.hideSoftInputFromWindow(view.windowToken, 0) ?: false
}

fun Fragment.clearAndNavigate(@IdRes toNavigate: Int) {
    val navBuilder = NavOptions.Builder()
    val navOptions: NavOptions = navBuilder.setPopUpTo(toNavigate, true).build()
    findNavController().navigate(toNavigate, null, navOptions)
}

/**
 * Sets the cursor at the end of this edit text and shows a keyboard
 */
fun Fragment.focusKeyboard(editText: EditText) {
    editText.requestFocus()
    editText.setSelection(editText.text.toString().length)
    showKeyboard(editText)
}