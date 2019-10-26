package com.joerakhimov.smartexpenses.screen.main.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.recyclerview.widget.RecyclerView
import com.joerakhimov.smartexpenses.R
import com.joerakhimov.smartexpenses.base.BaseRecyclerAdapter
import kotlinx.android.synthetic.main.listitem_profile_settings.view.*

class ProfileSettingsAdapter(val items: List<String>): BaseRecyclerAdapter(items) {

    override fun getLayoutRes() = R.layout.listitem_profile_settings

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.view.text_settings_item.text = item
    }

}