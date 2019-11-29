package com.joerakhimov.smartexpenses.screen.main.social

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.ipakyulibank.mobile.util.permissions.MyPermissionChecker
import com.ipakyulibank.mobile.util.permissions.MyPermissionListener
import com.joerakhimov.smartexpenses.base.BaseFragment
import com.joerakhimov.smartexpenses.di.Injector
import kotlinx.android.synthetic.main.fragment_social.*
import javax.inject.Inject

class SocialFragment : BaseFragment(), OnMapReadyCallback {

    private lateinit var viewModel: SocialViewModel

    private lateinit var mMap: GoogleMap

    @Inject
    lateinit var permissionChecker: MyPermissionChecker

    override fun getLayoutRes() = com.joerakhimov.smartexpenses.R.layout.fragment_social

    init {
        Injector.appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Injector.appComponent.inject(this)
        viewModel =
            ViewModelProviders.of(this).get(SocialViewModel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager
            .findFragmentById(com.joerakhimov.smartexpenses.R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        observeToastMessage()
        checkLocationPermission()
        observeLocation()
    }

    private fun observeLocation() {
        viewModel.location.observe(this, Observer {
            val location = it
            if (location != null) {
                buttonNavigation.isVisible = true
                buttonNavigation.setOnClickListener {
                    mMap.animateCamera(
                        CameraUpdateFactory.newLatLngZoom(
                            LatLng(
                                location.latitude,
                                location.longitude
                            ), 13F
                        )
                    )
                }
            } else {
                buttonNavigation.isVisible = false
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

    private fun checkLocationPermission() {
        permissionChecker.checkFineLocationPermission(activity, object : MyPermissionListener {
            override fun onAllow() {
                viewModel.startLocationListening()
            }

            override fun onDeny() {}
        })
    }

    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap

        val center = LatLng(47.497, 19.050)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(center, 13F))

        viewModel.locations.observe(this, Observer {
            mMap.clear()
            it.forEach {
                val marker = mMap.addMarker(MarkerOptions().position(LatLng(it?.latitude!!, it?.longitude!!)).title(it.title))
                marker.tag = it.id
            }
        }
        )

        mMap.setOnInfoWindowClickListener { var args = bundleOf("id" to it.tag)
            findNavController().navigate(com.joerakhimov.smartexpenses.R.id.navigation_details, args) }

    }

}