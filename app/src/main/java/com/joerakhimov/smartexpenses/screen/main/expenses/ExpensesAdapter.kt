package com.joerakhimov.smartexpenses.screen.main.expenses

import com.joerakhimov.smartexpenses.R
import com.joerakhimov.smartexpenses.base.BaseRecyclerAdapter
import com.joerakhimov.smartexpenses.base.RecyclerItemClickListener
import com.joerakhimov.smartexpenses.events.ExpenseLongClickEvent
import com.joerakhimov.smartexpenses.screen.main.home.model.ExpensesItem
import kotlinx.android.synthetic.main.listitem_expense.view.*
import org.greenrobot.eventbus.EventBus

class ExpensesAdapter(private val expensesList: MutableList<ExpensesItem?> = mutableListOf<ExpensesItem?>()): BaseRecyclerAdapter(expensesList) {

    override fun getLayoutRes() = R.layout.listitem_expense

    var onItemListener: RecyclerItemClickListener? = null

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val expense = expensesList[position]

        holder.view.text_title.text = expense?.title
        holder.view.text_address.text = expense?.address
        holder.view.text_amount.text = "${expense?.value} ${expense?.currency}"

        holder.view.setOnLongClickListener {
            EventBus.getDefault().post(ExpenseLongClickEvent(expense?.id))
            return@setOnLongClickListener true
        }

        when(expense?.categoryID){
            0->holder.view.image_icon.setImageResource(R.drawable.category_restaurant)
            1->holder.view.image_icon.setImageResource(R.drawable.category_tickets)
            2->holder.view.image_icon.setImageResource(R.drawable.category_museum)
            3->holder.view.image_icon.setImageResource(R.drawable.category_hotel)
            4->holder.view.image_icon.setImageResource(R.drawable.category_cash)
            5->holder.view.image_icon.setImageResource(R.drawable.category_shopping)
            6->holder.view.image_icon.setImageResource(R.drawable.category_gas)
            7->holder.view.image_icon.setImageResource(R.drawable.category_travel)
            8->holder.view.image_icon.setImageResource(R.drawable.category_other)
        }

        holder.view.setOnClickListener {
            onItemListener?.onClick(expense)
        }

    }

    fun updateList(items: List<ExpensesItem?>) {
        expensesList.clear()
        expensesList.addAll(items)
        notifyDataSetChanged()
    }

    fun setClickListener(listener: RecyclerItemClickListener){
        onItemListener = listener
    }

}