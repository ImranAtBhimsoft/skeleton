package com.primelab.skeleton.networks.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


/**
 * Created by m.imran
 * Senior Software Engineer at
 * PrimeLab.io on 09/02/2022.
 */
@Parcelize
@Entity
data class User(
    @PrimaryKey
    val id: Int,
    val name: String
) : Parcelable