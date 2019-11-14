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
import com.joerakhimov.smartexpenses.databinding.FragmentExpensesBinding
import com.joerakhimov.smartexpenses.databinding.FragmentHomeBinding
import com.joerakhimov.smartexpenses.screen.main.expenses.ExpensesAdapter
import com.joerakhimov.smartexpenses.screen.main.expenses.ExpensesViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.recycler_expenses

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

        showPlaces()

        observeExpenses()
        observeToastMessage()

    }

    private fun showPlaces() {
        recycler_places.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);

        val places = arrayListOf(
            R.drawable.budapest1,
            R.drawable.budapest2,
            R.drawable.budapest3,
            R.drawable.budapest4,
            R.drawable.budapest5
        )

        recycler_places.adapter = PlacesAdapter(places)
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