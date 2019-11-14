package com.joerakhimov.smartexpenses.helper.observer;

import androidx.databinding.BindingAdapter;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class CustomBindingAdapter {

//    @BindingAdapter("bind:imageUrl")
//    public static void loadImage(ImageView imageView, String urlImage) {
//        Picasso.with(imageView.getContext()).load(urlImage).into(imageView);
//    }
//
//    @BindingAdapter({"bind:data", "bind:clickHandler"})
//    public static void configureRecyclerView(RecyclerView recyclerView, List<Project> projects, ProjectsAdapter.OnItemClickListener listener) {
//        ProjectsAdapter adapter = new ProjectsAdapter(projects, listener);
//        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
//        recyclerView.setAdapter(adapter);
//    }

    @BindingAdapter({"bind:refreshState", "bind:onRefresh"})
    public static void configureSwipeRefreshLayout(SwipeRefreshLayout layout, boolean isLoading, SwipeRefreshLayout.OnRefreshListener listener) {
        layout.setOnRefreshListener(listener);
        layout.post(() -> layout.setRefreshing(isLoading));
    }

}