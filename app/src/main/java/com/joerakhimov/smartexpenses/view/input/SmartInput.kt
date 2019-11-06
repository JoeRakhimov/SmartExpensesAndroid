package com.joerakhimov.smartexpenses.view.input

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.joerakhimov.smartexpenses.R
import kotlinx.android.synthetic.main.smart_input.view.*
import android.text.method.PasswordTransformationMethod
import android.R.attr.password
import android.graphics.Typeface

class SmartInput(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private var mInputLabel: String? = null
    private var mInputHint: String? = null
    private var mInputType: Int = InputType.TYPE_CLASS_TEXT

    init {

        getAttributes(context, attrs)

        LayoutInflater.from(context).inflate(R.layout.smart_input, this, true)

        initLabel()
        initInput()

    }

    private fun getAttributes(context: Context, attrs: AttributeSet) {

        val a = context.obtainStyledAttributes(attrs, R.styleable.SmartInput)

        if (a.hasValue(R.styleable.SmartInput_smartInputLabel)) {
            mInputLabel = a.getString(R.styleable.SmartInput_smartInputLabel)
        }

        if (a.hasValue(R.styleable.SmartInput_smartInputHint)) {
            mInputHint = a.getString(R.styleable.SmartInput_smartInputHint)
        }

        if (a.hasValue(R.styleable.SmartInput_android_inputType)) {
            mInputType =
                a.getInt(R.styleable.SmartInput_android_inputType, InputType.TYPE_CLASS_TEXT)
        }

        a.recycle()

    }


    private fun initLabel() {
        if (mInputLabel != null) smartInputLabel.text = mInputLabel?.toUpperCase()
    }

    private fun initInput() {
        if (mInputHint != null) smartInput.hint = mInputHint
        when (mInputType) {
            InputType.TYPE_TEXT_VARIATION_PASSWORD or InputType.TYPE_CLASS_TEXT -> {
                smartInput.typeface = Typeface.DEFAULT
                smartInput.transformationMethod = PasswordTransformationMethod()
            }
            else -> smartInput.inputType = mInputType
        }
    }

    fun getText() = smartInput.text.toString()

}