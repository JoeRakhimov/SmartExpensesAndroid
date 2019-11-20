package com.joerakhimov.smartexpenses.view.spinner

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import com.joerakhimov.smartexpenses.R
import com.joerakhimov.smartexpenses.di.Injector
import kotlinx.android.synthetic.main.smart_spinner.view.*
import javax.inject.Inject


class SmartSpinner(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private var mInputLabel: String? = null

    @Inject
    lateinit var mContext: Context

    init {

        Injector.appComponent.inject(this)

        getAttributes(context, attrs)

        LayoutInflater.from(context).inflate(R.layout.smart_spinner, this, true)

        initLabel()

        initSpinner()

    }

    private fun getAttributes(context: Context, attrs: AttributeSet) {

        val a = context.obtainStyledAttributes(attrs, R.styleable.SmartSpinner)

        if (a.hasValue(R.styleable.SmartSpinner_smartSpinnerLabel)) {
            mInputLabel = a.getString(R.styleable.SmartSpinner_smartSpinnerLabel)
        }

        a.recycle()

    }


    private fun initLabel() {
        if (mInputLabel != null) smartSpinnerLabel.text = mInputLabel?.toUpperCase()
    }

    private fun initSpinner() {
        val spinnerArrayAdapter = ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.categories))
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        smartSpinner.adapter = spinnerArrayAdapter
    }

    fun getSelectedPosition()= smartSpinner.selectedItemPosition

}