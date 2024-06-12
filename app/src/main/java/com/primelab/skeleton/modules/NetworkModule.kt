package com.primelab.skeleton.modules

import com.primelab.common.di.BaseUrl
import com.primelab.common.network.BaseNetworkModuleImpl
import com.primelab.common.session.UserSession
import com.primelab.skeleton.networks.AuthInterceptor
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

//    override fun getBaseUrl(): String {   //alag fil m
//        return "https://lmn.opq.rs"
//    }
//
//    @BaseUrl
//    @Singleton
//    @Provides
//    fun provideBaseUrl() = getBaseUrl()

    @Provides
    @Singleton
    fun provideHttpClient(userSession: UserSession): OkHttpClient =
        getOkHttpClient(AuthInterceptor(userSession))

    @Provides
    @Singleton
    fun provideLoginApi(okHttpClient: OkHttpClient): UserApi =
        getAPi(getRetrofit(okHttpClient), UserApi::class.java)
}