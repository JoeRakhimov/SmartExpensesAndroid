package com.joerakhimov.smartexpenses.screen.main.expenses

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.joerakhimov.smartexpenses.base.BaseFragment
import com.joerakhimov.smartexpenses.base.RecyclerItemClickListener
import com.joerakhimov.smartexpenses.databinding.FragmentExpensesBinding
import com.joerakhimov.smartexpenses.events.ExpenseLongClickEvent
import com.joerakhimov.smartexpenses.screen.main.addexpense.AddExpenseFragment
import com.joerakhimov.smartexpenses.screen.main.home.model.ExpensesItem
import kotlinx.android.synthetic.main.fragment_expenses.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe


class ExpensesFragment : BaseFragment() {

    override fun getLayoutRes() = com.joerakhimov.smartexpenses.R.layout.fragment_expenses

    private lateinit var viewModel: ExpensesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val binding = FragmentExpensesBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(this).get(ExpensesViewModel::class.java)
        binding.vm = viewModel
        binding.lifecycleOwner = this
        return binding.getRoot()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeExpenses()
        observeToastMessage()
        initAddButton()

    }

    private fun showAddExpenseDialog() {
        val ft = fragmentManager!!.beginTransaction()
        val newFragment = AddExpenseFragment.newInstance()
        newFragment.show(ft, "dialog")
    }

    private fun initAddButton() {
        buttonAdd.setOnClickListener {
            showAddExpenseDialog()
        }
    }

    private fun observeExpenses() {
        val adapter = ExpensesAdapter()
        recycler_expenses.layoutManager = LinearLayoutManager(context)
        recycler_expenses.adapter = adapter
        adapter.setClickListener(object: RecyclerItemClickListener{
            override fun onClick(item: Any?) {
                if(item is ExpensesItem){
                    var args = bundleOf("id" to item.id)
                    findNavController().navigate(com.joerakhimov.smartexpenses.R.id.navigation_details, args)
                }
            }
        })
        viewModel.expenses.observe(this, Observer {
            if (it != null) adapter.updateList(it)
        })
    }

    private fun observeToastMessage() {
        viewModel.toastMessage.observe(this, Observer { any ->
            var message = ""
            when (any) {
                is Int -> message = getString(any)
                is String -> message = any
            }
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        })
    }

    @Subscribe
    fun onExpenseLongClick(event: ExpenseLongClickEvent) {
        showDeleteExpenseDialog(event.expenseId)
    }

    private fun showDeleteExpenseDialog(expenseId: Int?) {
        val dialog =
            activity?.let { AlertDialog.Builder(it) }
            ?.setTitle(com.joerakhimov.smartexpenses.R.string.delete_expense)
            ?.setMessage(com.joerakhimov.smartexpenses.R.string.are_you_sure_to_delete_expense)
            ?.setNegativeButton(com.joerakhimov.smartexpenses.R.string.cancel, object: DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    dialog?.dismiss()
                }
            })
            ?.setPositiveButton(com.joerakhimov.smartexpenses.R.string.delete, object: DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    viewModel.deleteExpense(expenseId)
                }
            })
            ?.create()
        dialog?.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

}