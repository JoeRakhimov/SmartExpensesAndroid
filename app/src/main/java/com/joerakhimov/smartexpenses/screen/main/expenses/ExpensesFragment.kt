package com.joerakhimov.smartexpenses.screen.main.expenses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.joerakhimov.smartexpenses.R
import com.joerakhimov.smartexpenses.base.BaseFragment
import com.joerakhimov.smartexpenses.databinding.FragmentExpensesBinding
import com.joerakhimov.smartexpenses.databinding.FragmentProfileBinding
import com.joerakhimov.smartexpenses.screen.main.profile.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_expenses.*

class ExpensesFragment : BaseFragment(){

    override fun getLayoutRes() = R.layout.fragment_expenses

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

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeExpenses()
        observeToastMessage()

    }

    private fun observeExpenses() {
        val adapter = ExpensesAdapter()
        recycler_expenses.layoutManager = LinearLayoutManager(context)
        recycler_expenses.adapter = adapter
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

}