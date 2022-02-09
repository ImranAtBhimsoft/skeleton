package com.primelab.common.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by m.imran
 * Senior Software Engineer at
 * PrimeLab.io on 09/02/2022.
 */
abstract class BaseNetworkModuleImpl : BaseNetworkModule {

    override fun getRetrofit(suffix: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(getBaseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient())
            .build()

    override fun getOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().also {
            it.level = HttpLoggingInterceptor.Level.BODY
        }
        val okhttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
        getAuthInterceptor()?.let {
            okhttpClient.addInterceptor(it)
        }
        return okhttpClient.build()
    }

    override fun <T> getAPi(retrofit: Retrofit, clazz: Class<T>): T {
        return retrofit.create(clazz)
    }

    override fun getAuthInterceptor(): Interceptor? {
        return null
    }
}