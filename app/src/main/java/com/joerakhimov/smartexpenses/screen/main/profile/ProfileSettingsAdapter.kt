package com.joerakhimov.smartexpenses.screen.main.profile

import android.view.View
import com.joerakhimov.smartexpenses.R
import com.joerakhimov.smartexpenses.base.BaseRecyclerAdapter
import kotlinx.android.synthetic.main.listitem_profile_settings.view.*

class ProfileSettingsAdapter(val items: List<ProfileScreenListItem>): BaseRecyclerAdapter(items) {

    override fun getLayoutRes() = R.layout.listitem_profile_settings

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.view.text_settings_item.text = holder.view.context.getString(item.title)
        holder.view.setOnClickListener(item.clickListener)
        holder.view.image_expand_button.visibility = if(item.clickListener!=null) View.VISIBLE else View.GONE
    }

}