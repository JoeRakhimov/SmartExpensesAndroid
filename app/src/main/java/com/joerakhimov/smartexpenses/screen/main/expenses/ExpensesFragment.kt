package com.joerakhimov.smartexpenses.screen.main.expenses

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.joerakhimov.smartexpenses.R
import com.joerakhimov.smartexpenses.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_expenses.*

class ExpensesFragment : BaseFragment(){

    override fun getLayoutRes() = R.layout.fragment_expenses

    private lateinit var dashboardViewModel: ExpensesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dashboardViewModel = ViewModelProviders.of(this).get(ExpensesViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_expenses.layoutManager = LinearLayoutManager(context)

        val expenses = arrayListOf(
            Expense("Potato hotpot", R.drawable.dinner, "1051 Budapest V. ketület Dorotya utca 4,", 1490.0, "HUF"),
            Expense("Potato hotpot", R.drawable.dinner, "1051 Budapest V. ketület Dorotya utca 4,", 1490.0, "HUF"),
            Expense("Potato hotpot", R.drawable.dinner, "1051 Budapest V. ketület Dorotya utca 4,", 1490.0, "HUF"),
            Expense("Potato hotpot", R.drawable.dinner, "1051 Budapest V. ketület Dorotya utca 4,", 1490.0, "HUF"),
            Expense("Potato hotpot", R.drawable.dinner, "1051 Budapest V. ketület Dorotya utca 4,", 1490.0, "HUF"),
            Expense("Potato hotpot", R.drawable.dinner, "1051 Budapest V. ketület Dorotya utca 4,", 1490.0, "HUF"),
            Expense("Potato hotpot", R.drawable.dinner, "1051 Budapest V. ketület Dorotya utca 4,", 1490.0, "HUF"),
            Expense("Potato hotpot", R.drawable.dinner, "1051 Budapest V. ketület Dorotya utca 4,", 1490.0, "HUF"),
            Expense("Potato hotpot", R.drawable.dinner, "1051 Budapest V. ketület Dorotya utca 4,", 1490.0, "HUF"),
            Expense("Potato hotpot", R.drawable.dinner, "1051 Budapest V. ketület Dorotya utca 4,", 1490.0, "HUF"),
            Expense("Potato hotpot", R.drawable.dinner, "1051 Budapest V. ketület Dorotya utca 4,", 1490.0, "HUF"),
            Expense("Potato hotpot", R.drawable.dinner, "1051 Budapest V. ketület Dorotya utca 4,", 1490.0, "HUF"),
            Expense("Potato hotpot", R.drawable.dinner, "1051 Budapest V. ketület Dorotya utca 4,", 1490.0, "HUF"),
            Expense("Potato hotpot", R.drawable.dinner, "1051 Budapest V. ketület Dorotya utca 4,", 1490.0, "HUF"),
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