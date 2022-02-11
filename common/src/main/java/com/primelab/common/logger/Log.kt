package com.primelab.common.logger

import android.util.Log
import androidx.annotation.Size
import com.primelab.common.BuildConfig

/**
 * Created by m.imran
 * Senior Software Engineer at
 * PrimeLab.io on 11/02/2022.
 */
class Log {
    companion object {
        @JvmStatic
        fun d(@Size(max = 23) tag: String, message: String) {
            if (BuildConfig.DEBUG)
                Log.d(tag, message)
        }

        @JvmStatic
        fun d(@Size(max = 23) tag: String, message: String, throwable: Throwable) {
            if (BuildConfig.DEBUG)
                Log.d(tag, message, throwable)
        }


        @JvmStatic
        fun i(@Size(max = 23) tag: String, message: String) {
            if (BuildConfig.DEBUG)
                Log.i(tag, message)
        }

        @JvmStatic
        fun i(@Size(max = 23) tag: String, message: String, throwable: Throwable) {
            if (BuildConfig.DEBUG)
                Log.i(tag, message, throwable)
        }

        @JvmStatic
        fun w(@Size(max = 23) tag: String, message: String) {
            if (BuildConfig.DEBUG)
                Log.w(tag, message)
        }

        @JvmStatic
        fun w(@Size(max = 23) tag: String, message: String, throwable: Throwable) {
            if (BuildConfig.DEBUG)
                Log.w(tag, message, throwable)
        }

        @JvmStatic
        fun e(@Size(max = 23) tag: String, message: String) {
            if (BuildConfig.DEBUG)
                Log.e(tag, message)
        }

        @JvmStatic
        fun e(@Size(max = 23) tag: String, message: String, throwable: Throwable) {
            if (BuildConfig.DEBUG)
                Log.e(tag, message, throwable)
        }
    }
}