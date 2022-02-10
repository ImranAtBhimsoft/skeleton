package com.primelab.common.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.primelab.common.session.UserToken

/**
 * Created by m.imran
 * Senior Software Engineer at
 * PrimeLab.io on 10/02/2022.
 */
@Dao
interface UserTokenDao {
    @Query("SELECT * FROM usertoken LIMIT 1")
    suspend fun getToken(): UserToken?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(token: UserToken)
}