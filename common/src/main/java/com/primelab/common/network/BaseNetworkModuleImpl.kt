package com.primelab.common.network

import com.primelab.common.di.BaseUrl
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
    companion object{
        const val BASE_URL="https://lmn.opq.rs"
    }
    override fun getRetrofit(httpClient: OkHttpClient, suffix: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()

    override fun getOkHttpClient(autInterceptor: Interceptor?): OkHttpClient {
        val logging = HttpLoggingInterceptor().also {
            it.level = HttpLoggingInterceptor.Level.BODY
        }
        val okhttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
        autInterceptor?.let {
            okhttpClient.addInterceptor(it)
        }
        return okhttpClient.build()
    }

    override fun <T> getAPi(retrofit: Retrofit, clazz: Class<T>): T {
        return retrofit.create(clazz)
    }
}