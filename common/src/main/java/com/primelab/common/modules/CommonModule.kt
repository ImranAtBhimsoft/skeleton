package com.primelab.common.modules

import com.primelab.common.database.dao.UserTokenDao
import com.primelab.common.session.UserSession
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by m.imran
 * Senior Software Engineer at
 * PrimeLab.io on 10/02/2022.
 */
@Module
@InstallIn(SingletonComponent::class)
class CommonModule {
    @Provides
    @Singleton
    fun provideHUserSession(userDao: UserTokenDao): UserSession = UserSession(userDao)
}