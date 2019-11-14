package com.joerakhimov.smartexpenses.screen.main.expenses

import com.joerakhimov.smartexpenses.R
import com.joerakhimov.smartexpenses.base.BaseRecyclerAdapter
import com.joerakhimov.smartexpenses.screen.main.expenses.model.ExpensesItem
import kotlinx.android.synthetic.main.listitem_expense.view.*

class ExpensesAdapter(private val expensesList: MutableList<ExpensesItem?> = mutableListOf<ExpensesItem?>()): BaseRecyclerAdapter(expensesList) {

    override fun getLayoutRes() = R.layout.listitem_expense

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val expense = expensesList[position]

        holder.view.text_title.text = expense?.title
        holder.view.image_icon.setImageResource(R.drawable.dinner)
        holder.view.text_address.text = expense?.address
        holder.view.text_amount.text = "${expense?.value} ${expense?.currency}"

    }

    fun updateList(items: List<ExpensesItem?>) {
        expensesList.clear()
        expensesList.addAll(items)
        notifyDataSetChanged()
    }

}