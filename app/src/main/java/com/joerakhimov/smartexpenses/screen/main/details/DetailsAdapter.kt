package com.joerakhimov.smartexpenses.screen.main.details

import com.joerakhimov.smartexpenses.R
import com.joerakhimov.smartexpenses.base.BaseRecyclerAdapter
import kotlinx.android.synthetic.main.listitem_detail.view.*

class DetailsAdapter(private val detailsList: MutableList<DetailItem?> = mutableListOf<DetailItem?>()): BaseRecyclerAdapter(detailsList) {

    override fun getLayoutRes() = R.layout.listitem_detail

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val detail = detailsList[position]

        if(detail?.title!=null) holder.view.text_title.setText(detail.title!!)
        holder.view.text_value.text = detail?.value

    }

    fun updateList(items: List<DetailItem?>) {
        detailsList.clear()
        detailsList.addAll(items)
        notifyDataSetChanged()
    }

}