package com.primelab.common.modules

import android.content.Context
import android.content.SharedPreferences
import com.primelab.common.database.dao.UserTokenDao
import com.primelab.common.di.PreferenceName
import com.primelab.common.session.UserSession
import com.primelab.common.sharedpreferences.SharedPrefs
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
class CommonModule {
    @Provides
    @Singleton
    fun provideHUserSession(userDao: UserTokenDao): UserSession = UserSession(userDao)

    @PreferenceName
    @Provides
    @Singleton
    fun provideSharePrefsName() = "near-nft-prefs"

    @Provides
    @Singleton
    fun provideSharePreference(
        @ApplicationContext context: Context,
        @PreferenceName name: String
    ): SharedPreferences {
        return context.getSharedPreferences(name, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideSharePrefs(sharedPreferences: SharedPreferences) = SharedPrefs(sharedPreferences)
}