package com.primelab.common.widgets

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.text.InputFilter
import android.text.InputType
import android.util.AttributeSet
import android.view.Gravity
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.doAfterTextChanged
import com.primelab.common.R
import com.primelab.common.logger.Log

/**
 * Created by m.imran
 * Senior Software Engineer at
 * PrimeLab.io on 17/02/2022.
 */
class OtpView : LinearLayoutCompat {
    private var defaultCount: Int = 5
    private var focusColor: Int = Color.GREEN
    private var unFocusColor: Int = Color.GRAY
    private lateinit var stateColor: ColorStateList
    private val editTexts: MutableList<AppCompatEditText> = mutableListOf()
    private lateinit var errorMessage: String

    private lateinit var errorView: TextView

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(
        context: Context,
        attrs: AttributeSet? = null
    ) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0
    ) : super(context, attrs, defStyle) {
        init(context, attrs, defStyle)
    }

    private fun init(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0
    ) {
        orientation = VERTICAL
        context.theme.obtainStyledAttributes(attrs, R.styleable.OtpView, defStyle, 0).apply {
            try {
                defaultCount = getInt(R.styleable.OtpView_count, 5)
                val msg = getString(R.styleable.OtpView_error_message)
                errorMessage = msg ?: context.getString(R.string.otp_error_message)
                val font = getResourceId(R.styleable.OtpView_android_fontFamily, 0)
                val fontRes = ResourcesCompat.getFont(context, font)
                Log.d(">>>OtpView", "Default count $fontRes")
            } finally {
                recycle()
            }
        }
        stateColor = ColorStateList(
            arrayOf(
                intArrayOf(-android.R.attr.state_focused),  // Disabled
                intArrayOf(android.R.attr.state_focused)    // Enabled
            ),
            intArrayOf(unFocusColor, focusColor)
        )
        setUpView()
    }

    private fun setUpView() {
        val itemViewContainer = LinearLayoutCompat(context)
        itemViewContainer.layoutParams =
            LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT).apply {
                gravity = Gravity.CENTER
            }
        for (i in 0 until defaultCount) {
            val edt = AppCompatEditText(context)
            edt.layoutParams =
                LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT).apply {
                    weight = 1F
                    gravity = Gravity.CENTER
                    marginStart =
                        if (i != 0) resources.getDimensionPixelSize(R.dimen.otp_item_space) else 0
                }
            edt.inputType = InputType.TYPE_CLASS_NUMBER
            edt.hint = "0"
            edt.imeOptions =
                if (i < defaultCount - 1) EditorInfo.IME_ACTION_NEXT else EditorInfo.IME_ACTION_DONE
            edt.tag = i
            edt.filters = arrayOf(InputFilter.LengthFilter(1))
            edt.gravity = Gravity.CENTER
            edt.background = ContextCompat.getDrawable(context, R.drawable.selector_otp)
            itemViewContainer.addView(edt)
            editTexts.add(edt)
        }
        addView(itemViewContainer)
        setUpErrorView()
        setListener()
    }

    private fun setUpErrorView() {
        errorView = TextView(context)
        errorView.setTextColor(ContextCompat.getColor(context, R.color.otp_color_error))
        errorView.visibility = INVISIBLE
        errorView.text = errorMessage
        addView(errorView)
    }

    private fun setListener() {
        for (i in 0 until editTexts.size) {
            val edt = editTexts[i]
            edt.doAfterTextChanged {
                if (i < editTexts.size - 1 && it?.isNotEmpty() == true) {
                    editTexts[i + 1].requestFocus()
                }
                errorView.visibility = INVISIBLE
            }
            edt.setOnKeyListener { _, _, keyEvent ->
                if (keyEvent.action == KeyEvent.ACTION_DOWN
                    && keyEvent.keyCode in KeyEvent.KEYCODE_0..KeyEvent.KEYCODE_9
                    && edt.text?.length == 1
                ) {
                    edt.setText("")
                }
                false
            }
        }
    }

    fun setErrorMessage(@StringRes error: Int) {
        errorView.setText(error)
    }

    fun setErrorMessage(error: String) {
        errorView.text = error
    }

    fun isValid(): Boolean {
        for (i in 0 until editTexts.size) {
            val edt = editTexts[i]
            if (edt.text?.length == 0) {
                errorView.visibility = VISIBLE
                return false
            }
        }
        return true
    }
}