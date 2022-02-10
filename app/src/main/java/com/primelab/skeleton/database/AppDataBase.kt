package com.primelab.skeleton.database

import android.content.Context
import androidx.room.Database
import com.primelab.common.database.AppRoomDataBase
import com.primelab.common.session.UserToken
import com.primelab.skeleton.database.daos.UserDao
import com.primelab.skeleton.networks.models.User

/**
 * Created by m.imran
 * Senior Software Engineer at
 * PrimeLab.io on 10/02/2022.
 */
@Database(
    entities = [UserToken::class, User::class],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase : AppRoomDataBase() {
    abstract fun getUserDao(): UserDao

    companion object {
        @Volatile
        private var instance: AppDataBase? = null
        fun getInstance(context: Context): AppDataBase =
            instance ?: synchronized(this) {
                instance ?: getRoomDatabase(context, "skeleton", AppDataBase::class.java).also {
                    instance = it
                }
            }
    }
}