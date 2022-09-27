package com.primelab.common.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import android.util.Property
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
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



class token @Inject constructor(@ApplicationContext context: Context){
    var pref=context.getSharedPreferences("xyz",Context.MODE_PRIVATE)
fun savetoken(token: String){
    val edi=pref.edit()
    edi.putString("usertoken",token)
    edi.apply()
}
    fun gettoken():String?{
        return pref.getString("usertoken",null)
    }
}