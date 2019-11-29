package com.joerakhimov.smartexpenses.screen.main.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.joerakhimov.smartexpenses.R
import com.joerakhimov.smartexpenses.base.BaseFragment
import com.joerakhimov.smartexpenses.databinding.DetailsFragmentBinding
import kotlinx.android.synthetic.main.details_fragment.*
import java.text.SimpleDateFormat
import java.util.*

class DetailsFragment : BaseFragment(), OnMapReadyCallback {

    override fun getLayoutRes() = R.layout.details_fragment

    companion object {
        fun newInstance() = DetailsFragment()
    }

    private lateinit var viewModel: DetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val binding = DetailsFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(this).get(DetailsViewModel::class.java)
        binding.vm = viewModel
        binding.lifecycleOwner = this
        return binding.getRoot()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getExpense(arguments?.getInt("id"))
        initList()
        observeToastMessage()
        initMap()
    }

    private fun initMap() {
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    var mMap: GoogleMap? = null

    private fun initList() {
        val adapter = DetailsAdapter()
        recycler_details.layoutManager = LinearLayoutManager(context)
        recycler_details.adapter = adapter
        viewModel.expense.observe(this, Observer {
            if (it != null){
                if(it.latitude!=null && it.longitude!=null){
                    mMap?.addMarker(MarkerOptions().position(LatLng(it?.latitude!!, it?.longitude!!)).title(it.title))
                    mMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(it.latitude!!, it.longitude!!), 13F))
                }
                var category = context?.resources?.getStringArray(R.array.categories)?.get(it.categoryID!!)
                val details = arrayListOf(
                    DetailItem(R.string.date, SimpleDateFormat("dd.MM.yyyy hh:MM").format(Date(it.date!!*1000))),
                    DetailItem(R.string.category, category),
                    DetailItem(R.string.title, it.title),
                    DetailItem(R.string.amount, it.value.toString()),
                    DetailItem(R.string.currency, it.currency),
                    DetailItem(R.string.amount_in_usd, it.valueUSD.toString()),
                    DetailItem(R.string.address, it.address)
                )
                adapter.updateList(details)
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

    override fun onMapReady(map: GoogleMap?) {
        mMap = map
    }

}
