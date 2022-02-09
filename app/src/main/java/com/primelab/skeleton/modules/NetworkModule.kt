package com.primelab.skeleton.modules

import com.primelab.common.di.BaseUrl
import com.primelab.common.network.BaseNetworkModuleImpl
import com.primelab.skeleton.networks.UserApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

/**
 * Created by m.imran
 * Senior Software Engineer at
 * PrimeLab.io on 09/02/2022.
 */
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule : BaseNetworkModuleImpl() {
    override fun getBaseUrl(): String {
        return "https://lmn.opq.rs"
    }

    @BaseUrl
    @Singleton
    @Provides
    fun provideBaseUrl() = getBaseUrl()

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient = getOkHttpClient()

    @Provides
    @Singleton
    fun provideLoginApi(): UserApi = getAPi(getRetrofit(), UserApi::class.java)
}