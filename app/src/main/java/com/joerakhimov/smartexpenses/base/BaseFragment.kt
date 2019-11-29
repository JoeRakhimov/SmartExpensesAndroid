package com.joerakhimov.smartexpenses.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.navOptions
import com.joerakhimov.smartexpenses.R

abstract class BaseFragment: Fragment() {

    abstract fun getLayoutRes(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutRes(), container, false)
    }

    fun getNavOptions() = navOptions {
        anim {
            enter = R.anim.slide_in_bottom
            exit = R.anim.slide_out_top
            popEnter = R.anim.slide_in_top
            popExit = R.anim.slide_out_bottom
        }
    }

}