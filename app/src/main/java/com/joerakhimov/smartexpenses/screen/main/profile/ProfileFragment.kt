package com.joerakhimov.smartexpenses.screen.main.profile


import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager

import com.joerakhimov.smartexpenses.R
import com.joerakhimov.smartexpenses.base.BaseFragment
import com.joerakhimov.smartexpenses.helper.transformation.CircleTransform
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : BaseFragment() {

    override fun getLayoutRes() = com.joerakhimov.smartexpenses.R.layout.fragment_profile

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Picasso.get().load(R.drawable.profile).transform(CircleTransform()).into(image_profile)

        recycler_profile_settings.layoutManager = LinearLayoutManager(context)

        val settingsItems = arrayListOf(
            "Notifications",
            "SelectProfileColor",
            "Number of latest spendings",
            "Privacy",
            "Terms and Conditions")

        recycler_profile_settings.adapter = ProfileSettingsAdapter(settingsItems)

    }

}
