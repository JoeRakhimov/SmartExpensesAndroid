package com.joerakhimov.smartexpenses.helper.databinding

import android.graphics.Color
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.DrawableCompat
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.joerakhimov.smartexpenses.R

@BindingAdapter("bind:refreshState", "bind:onRefresh")
fun configureSwipeRefreshLayout(
    layout: SwipeRefreshLayout,
    isLoading: Boolean,
    listener: SwipeRefreshLayout.OnRefreshListener
) {
    layout.setOnRefreshListener(listener)
    layout.post { layout.isRefreshing = isLoading }
}

@BindingAdapter("bind:totalSpending")
fun setTotalSpending(
    view: TextView,
    totalSpending: Double) {
    view.text = "${view.resources.getString(R.string.total_spendings)}: $totalSpending HUF"
}

@BindingAdapter("bind:photoBackground")
fun setPhotoBackground(
    view: ImageView,
    hex: String?) {

    if(hex!=null){
        val unwrappedDrawable = AppCompatResources.getDrawable(view.context, R.drawable.profile_background);
        if(unwrappedDrawable!=null){
            val wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable)
            DrawableCompat.setTint(wrappedDrawable, Color.parseColor(hex))
            view.background = wrappedDrawable
        }
    }

}