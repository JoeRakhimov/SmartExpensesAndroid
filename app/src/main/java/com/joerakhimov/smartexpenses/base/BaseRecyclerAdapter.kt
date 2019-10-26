package com.joerakhimov.smartexpenses.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter(val baseRecyclerAdapterItems: List<Any?>): RecyclerView.Adapter<BaseRecyclerAdapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    abstract fun getLayoutRes(): Int

    override fun getItemCount() = baseRecyclerAdapterItems.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(getLayoutRes(), parent, false) as View
        return ViewHolder(view)
    }

}