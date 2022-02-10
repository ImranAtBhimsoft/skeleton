package com.primelab.skeleton.modules

import android.content.Context
import com.primelab.common.database.dao.UserTokenDao
import com.primelab.skeleton.database.AppDataBase
import com.primelab.skeleton.database.daos.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by m.imran
 * Senior Software Engineer at
 * PrimeLab.io on 10/02/2022.
 */
@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {
    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context) = AppDataBase.getInstance(context)

    @Provides
    @Singleton
    fun provideUserDao(dataBase: AppDataBase): UserDao = dataBase.getUserDao()

    @Provides
    @Singleton
    fun provideUserTokenDao(dataBase: AppDataBase): UserTokenDao = dataBase.getUserTokenDao()
}