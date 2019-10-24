package com.joerakhimov.smartexpenses.screen.auth.welcome

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.joerakhimov.smartexpenses.R
import com.joerakhimov.smartexpenses.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_welcome.*

class WelcomeFragment : BaseFragment() {

    override fun getLayoutRes() = R.layout.fragment_welcome

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        button_sign_in.setOnClickListener {
            findNavController().navigate(R.id.destination_login, null, getNavOptions())
        }

        button_sign_up.setOnClickListener {
            findNavController().navigate(R.id.destination_register, null, getNavOptions())
        }

        viewpager.adapter = WelcomePagerAdapter(
            childFragmentManager
        )
        indicator.setViewPager(viewpager)

    }

    private fun getNavOptions() = navOptions {
        anim {
            enter = R.anim.slide_in_bottom
            exit = R.anim.slide_out_top
            popEnter = R.anim.slide_in_top
            popExit = R.anim.slide_out_bottom
        }
    }

}
