package com.primelab.common.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.primelab.common.database.dao.UserTokenDao

/**
 * Created by m.imran
 * Senior Software Engineer at
 * PrimeLab.io on 10/02/2022.
 */
abstract class AppRoomDataBase : RoomDatabase() {
    abstract fun getUserTokenDao(): UserTokenDao
    companion object {
        fun <T : RoomDatabase> getRoomDatabase(
            context: Context,
            prefix: String,
            clazz: Class<T>
        ): T =
            Room.databaseBuilder(context, clazz, prefix + getName())
                .fallbackToDestructiveMigration()
                .build()

        private fun getName(): String = "_room_data_base"
    }
}