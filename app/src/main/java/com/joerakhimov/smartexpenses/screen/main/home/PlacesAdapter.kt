package com.joerakhimov.smartexpenses.screen.main.home

import android.widget.BaseAdapter
import com.joerakhimov.smartexpenses.R
import com.joerakhimov.smartexpenses.base.BaseRecyclerAdapter
import kotlinx.android.synthetic.main.listitem_place.view.*

class PlacesAdapter(val places: List<Int>): BaseRecyclerAdapter(places) {

    override fun getLayoutRes() = R.layout.listitem_place

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.image_place.setImageResource(places[position])
    }

}