package com.joerakhimov.smartexpenses.screen.main.profile

import android.view.View

data class ProfileScreenListItem(
    var title: Int,
    var clickListener: View.OnClickListener? = null
)