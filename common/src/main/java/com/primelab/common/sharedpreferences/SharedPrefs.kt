package com.primelab.common.sharedpreferences

import android.content.SharedPreferences
import android.util.Property
import kotlin.reflect.KProperty

/**
 * Created by m.imran
 * Senior Software Engineer at
 * PrimeLab.io on 11/02/2022.
 */
class SharedPrefs(private val sharePrefs: SharedPreferences) {
    var userId by sharePrefs.string()

    fun registerKeyChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener) {
        sharePrefs.registerOnSharedPreferenceChangeListener(listener)
    }

    fun unregisterKeyChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener) {
        sharePrefs.unregisterOnSharedPreferenceChangeListener(listener)
    }

    fun clear() {
        sharePrefs.delete()
    }

}