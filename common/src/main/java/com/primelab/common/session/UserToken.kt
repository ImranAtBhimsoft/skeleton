package com.primelab.common.session

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by m.imran
 * Senior Software Engineer at
 * PrimeLab.io on 10/02/2022.
 */
@Entity
data class UserToken(
    val token: String,
    @PrimaryKey
    var id: Int = 102
)