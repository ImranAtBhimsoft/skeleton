package com.primelab.common.widgets

import android.content.Context
import androidx.appcompat.widget.AppCompatImageView


/**
 * Created by m.imran
 * Senior Software Engineer at
 * PrimeLab.io on 11/02/2022.
 */
class SquaredImageView(context: Context) : AppCompatImageView(context) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }
}