package com.primelab.common.database.dao

import androidx.room.*
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
    suspend fun save(token: UserToken)

    @Query("DELETE FROM usertoken")
    suspend fun delete()
}