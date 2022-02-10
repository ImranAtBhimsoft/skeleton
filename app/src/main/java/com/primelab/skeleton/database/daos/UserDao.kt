package com.primelab.skeleton.database.daos

import androidx.room.Dao
import androidx.room.Query
import com.primelab.skeleton.networks.models.User
import javax.inject.Singleton

/**
 * Created by m.imran
 * Senior Software Engineer at
 * PrimeLab.io on 10/02/2022.
 */
@Singleton
@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    suspend fun getAll(): List<User>
}