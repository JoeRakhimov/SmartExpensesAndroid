package com.joerakhimov.smartexpenses.screen.main.home

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
import com.joerakhimov.smartexpenses.databinding.FragmentHomeBinding
import com.joerakhimov.smartexpenses.screen.main.expenses.ExpensesAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.recycler_expenses
import com.joerakhimov.smartexpenses.screen.main.addexpense.AddExpenseFragment


class HomeFragment : BaseFragment() {

    override fun getLayoutRes() = R.layout.fragment_home

    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        binding.vm = viewModel
        binding.lifecycleOwner = this
        return binding.getRoot()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeImages()
        observeExpenses()
        observeToastMessage()

        buttonAdd.setOnClickListener {
            showAddExpenseDialog()
        }

        viewModel.getExpenses()

    }

    private fun showAddExpenseDialog(){
        val ft = fragmentManager!!.beginTransaction()
        val newFragment = AddExpenseFragment.newInstance()
        newFragment.show(ft, "dialog")
    }

    private fun observeImages() {
        val adapter = PlacesAdapter()
        recycler_places.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recycler_places.adapter = adapter
        viewModel.images.observe(this, Observer {
            if (it != null) adapter.updateList(it)
        })
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