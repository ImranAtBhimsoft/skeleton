package com.primelab.common.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText

/**
 * Created by m.imran
 * Senior Software Engineer at
 * PrimeLab.io on 11/02/2022.
 */

fun Activity.startActivityWithDefaultAnimation(intent: Intent) {
    startActivity(intent)
}

fun Activity.startNewActivityWithDefaultAnimation(intent: Intent) {
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    startActivity(intent)
}

fun Activity.startActivityWithFadeInAnimation(intent: Intent) {
    startActivity(intent)
    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
}

fun Activity.endActivityWithFadeOutAnimation() {
    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
}

fun Activity.showLongToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Activity.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Activity.hideKeyboard(): Boolean {
    val view = window.currentFocus
    return hideKeyboard(window, view)
}

private fun hideKeyboard(window: Window, view: View?): Boolean {
    if (view == null) {
        return false
    }
    val inputMethodManager =
        window.context.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as? InputMethodManager
    return inputMethodManager?.hideSoftInputFromWindow(view.windowToken, 0) ?: false
}

/**
 * Sets the cursor at the end of this edit text and shows a keyboard
 */
fun Activity.focusKeyboard(editText: AppCompatEditText) {
    editText.requestFocus()
    editText.setSelection(editText.text.toString().length)
    showKeyboard(editText)
}

fun Activity.showKeyboard(view: View) {
    val manager = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    manager.showSoftInput(view, InputMethodManager.SHOW_FORCED)
}