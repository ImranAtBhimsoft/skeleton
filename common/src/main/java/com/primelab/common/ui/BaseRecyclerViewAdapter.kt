package com.primelab.common.ui

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by m.imran
 * Senior Software Engineer at
 * PrimeLab.io on 11/02/2022.
 */
abstract class BaseRecyclerViewAdapter<DATA, VH : RecyclerView.ViewHolder>(
    private val data: MutableList<DATA> = mutableListOf()
) : RecyclerView.Adapter<VH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return createViewHolderInternal(parent, viewType)
    }

    override fun getItemCount(): Int = data.size

    abstract fun createViewHolderInternal(parent: ViewGroup, viewType: Int): VH

    fun getItemAtPosition(position: Int): DATA? = data.getOrNull(position)

    fun removeItemAtPosition(position: Int): DATA? = data.removeAt(position)

    @SuppressLint("NotifyDataSetChanged")
    open fun setData(newItems: List<DATA>?) {
        data.clear()
        data.addAll(newItems ?: emptyList())
        notifyDataSetChanged()
    }

    @SuppressWarnings("UNCHECKED_CAST")
    open fun getData(): List<DATA>? {
        return data
    }
}