package com.joerakhimov.smartexpenses.screen.main.addexpense

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.Observable
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ipakyulibank.mobile.util.permissions.MyPermissionChecker
import com.ipakyulibank.mobile.util.permissions.MyPermissionListener
import com.joerakhimov.smartexpenses.databinding.AddExpenseFragmentBinding
import com.joerakhimov.smartexpenses.di.Injector
import kotlinx.android.synthetic.main.add_expense_fragment.*
import javax.inject.Inject

class AddExpenseFragment : DialogFragment() {

    @Inject
    lateinit var permissionChecker: MyPermissionChecker

    companion object {
        fun newInstance() = AddExpenseFragment()
    }

    private lateinit var viewModel: AddExpenseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Injector.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = AddExpenseFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(this).get(AddExpenseViewModel::class.java)
        binding.vm = viewModel
        binding.lifecycleOwner = this
        return binding.getRoot()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSaveButton()

        observeToastMessage()

        observeIsOpen()

        checkLocationPermission()

    }

    private fun checkLocationPermission() {
        permissionChecker.checkFineLocationPermission(activity, object : MyPermissionListener {
            override fun onAllow() {
                viewModel.startLocationListening()
            }

            override fun onDeny() {}
        })
    }

    private fun initSaveButton(){
        buttonSave.setOnClickListener {
            viewModel.save(inputName.getText(), inputAmount.getText(), spinnerCategory.getSelectedPosition())
        }
    }

    private fun observeIsOpen(){
        viewModel.isOpen.addOnPropertyChangedCallback(object: Observable.OnPropertyChangedCallback(){
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                dismiss()
            }
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
