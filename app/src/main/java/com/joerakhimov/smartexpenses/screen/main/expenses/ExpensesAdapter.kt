package com.joerakhimov.smartexpenses.screen.main.expenses

import com.joerakhimov.smartexpenses.R
import com.joerakhimov.smartexpenses.base.BaseRecyclerAdapter
import kotlinx.android.synthetic.main.listitem_expense.view.*

class ExpensesAdapter(val expenses: List<Expense>): BaseRecyclerAdapter(expenses) {

    override fun getLayoutRes() = R.layout.listitem_expense

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val expense = expenses[position]

        holder.view.text_title.text = expense.title
        holder.view.image_icon.setImageResource(expense.icon)
        holder.view.text_address.text = expense.address
        holder.view.text_amount.text = "${expense.amount} ${expense.currency}"

    }

}