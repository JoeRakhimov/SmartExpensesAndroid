package com.joerakhimov.smartexpenses.screen.main.home

import com.joerakhimov.smartexpenses.R
import com.joerakhimov.smartexpenses.base.BaseRecyclerAdapter
import kotlinx.android.synthetic.main.listitem_place.view.*
import com.squareup.picasso.Picasso

class PlacesAdapter(val places: List<Int>): BaseRecyclerAdapter(places) {

    override fun getLayoutRes() = R.layout.listitem_place

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.view.image_place.setImageResource(places[position])
        Picasso.get()
            .load(places[position])
            .into(holder.view.image_place)
    }

}