package com.joerakhimov.smartexpenses.view.button

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.joerakhimov.smartexpenses.R
import kotlinx.android.synthetic.main.smart_button.view.*

class SmartButton(context: Context, attrs: AttributeSet): LinearLayout(context, attrs) {

    private var mButtonText: String? = null

    init {

        val a = context.obtainStyledAttributes(attrs, R.styleable.SmartButton)
        if (a.hasValue(R.styleable.SmartButton_smartButtonText)) {
            mButtonText = a.getString(R.styleable.SmartButton_smartButtonText)
        }

        LayoutInflater.from(context).inflate(R.layout.smart_button, this, true)

        if(mButtonText!=null){
            smartButtonText.text = mButtonText
        }

    }

}