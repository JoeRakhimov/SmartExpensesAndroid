package com.joerakhimov.smartexpenses.screen.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.joerakhimov.smartexpenses.R
import com.joerakhimov.smartexpenses.base.BaseFragment
import com.joerakhimov.smartexpenses.screen.main.expenses.Expense
import com.joerakhimov.smartexpenses.screen.main.expenses.ExpensesAdapter
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {

    override fun getLayoutRes() = R.layout.fragment_home

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_places.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);

        val places = arrayListOf(
            R.drawable.budapest1,
            R.drawable.budapest2,
            R.drawable.budapest3,
            R.drawable.budapest4,
            R.drawable.budapest5
        )

        recycler_places.adapter = PlacesAdapter(places)

        recycler_expenses.layoutManager = LinearLayoutManager(context)

        val expenses = arrayListOf(
            Expense("Potato hotpot", R.drawable.dinner, "1051 Budapest V. ketület Dorotya utca 4,", 1490.0, "HUF"),
            Expense("Potato hotpot", R.drawable.dinner, "1051 Budapest V. ketület Dorotya utca 4,", 1490.0, "HUF"),
            Expense("Potato hotpot", R.drawable.dinner, "1051 Budapest V. ketület Dorotya utca 4,", 1490.0, "HUF"),
            Expense("Potato hotpot", R.drawable.dinner, "1051 Budapest V. ketület Dorotya utca 4,", 1490.0, "HUF"),
            Expense("Potato hotpot", R.drawable.dinner, "1051 Budapest V. ketület Dorotya utca 4,", 1490.0, "HUF"),
            Expense("Potato hotpot", R.drawable.dinner, "1051 Budapest V. ketület Dorotya utca 4,", 1490.0, "HUF"),
            Expense("Potato hotpot", R.drawable.dinner, "1051 Budapest V. ketület Dorotya utca 4,", 1490.0, "HUF")
        )

        recycler_expenses.adapter = ExpensesAdapter(expenses)

    }

}