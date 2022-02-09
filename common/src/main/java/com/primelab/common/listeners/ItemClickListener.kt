package com.primelab.common.listeners

/**
 * Created by m.imran
 * Senior Software Engineer at
 * PrimeLab.io on 09/02/2022.
 */
interface ItemClickListener<in T> {
    fun onItemClick(t: T)
}