package com.joerakhimov.smartexpenses.helper.databinding

import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

@BindingAdapter("bind:refreshState", "bind:onRefresh")
fun configureSwipeRefreshLayout(
    layout: SwipeRefreshLayout,
    isLoading: Boolean,
    listener: SwipeRefreshLayout.OnRefreshListener
) {
    layout.setOnRefreshListener(listener)
    layout.post { layout.isRefreshing = isLoading }
}