package com.joerakhimov.smartexpenses.screen.main.home

import com.joerakhimov.smartexpenses.R
import com.joerakhimov.smartexpenses.base.BaseRecyclerAdapter
import com.joerakhimov.smartexpenses.extension.newIntentToOpenUrlInBrowser
import com.joerakhimov.smartexpenses.screen.main.home.model.ImagesItem
import kotlinx.android.synthetic.main.listitem_place.view.*
import com.squareup.picasso.Picasso

class PlacesAdapter(private val imagesList: MutableList<ImagesItem?> = mutableListOf<ImagesItem?>()): BaseRecyclerAdapter(imagesList) {

    override fun getLayoutRes() = R.layout.listitem_place

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val image = imagesList[position]

        Picasso.get()
            .load(image?.imageUrl)
            .placeholder(R.drawable.placeholder)
            .into(holder.view.image_place)

        holder.view.setOnClickListener {
            holder.view.context.startActivity(newIntentToOpenUrlInBrowser(image?.websiteUrl))
        }

    }

    fun updateList(items: List<ImagesItem?>) {
        imagesList.clear()
        imagesList.addAll(items)
        notifyDataSetChanged()
    }

}