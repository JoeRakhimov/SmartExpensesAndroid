package com.joerakhimov.smartexpenses.screen.auth.welcome

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.joerakhimov.smartexpenses.R
import com.joerakhimov.smartexpenses.base.BaseFragment
import com.joerakhimov.smartexpenses.screen.main.MainActivity
import kotlinx.android.synthetic.main.fragment_welcome.*

class WelcomeFragment : BaseFragment() {

    private lateinit var viewModel: WelcomeViewModel

    override fun getLayoutRes() = R.layout.fragment_welcome

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel = ViewModelProviders.of(this).get(WelcomeViewModel::class.java)

        initSignInButton()

        initSignUpButton()

        viewpager.adapter = WelcomePagerAdapter(
            childFragmentManager
        )

        indicator.setViewPager(viewpager)

        viewModel.userLoggedInEvent.observe(this, Observer { isLoggedIn ->
            if (isLoggedIn) {
                startActivity(Intent(activity, MainActivity::class.java))
                activity?.finish()
            }
        })

    }

    private fun initSignUpButton() {
        button_sign_up.setOnClickListener {
            findNavController().navigate(R.id.destination_register, null, getNavOptions())
        }
    }

    private fun initSignInButton() {
        button_sign_in.setOnClickListener {
            findNavController().navigate(R.id.destination_login, null, getNavOptions())
        }
    }

}
