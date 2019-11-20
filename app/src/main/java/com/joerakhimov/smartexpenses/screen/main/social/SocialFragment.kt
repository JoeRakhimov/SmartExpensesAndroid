package com.joerakhimov.smartexpenses.screen.main.social

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.ClusterManager
import com.joerakhimov.smartexpenses.base.BaseFragment
import com.joerakhimov.smartexpenses.screen.main.social.model.LocationItem

class SocialFragment : BaseFragment(), OnMapReadyCallback {

    private var mClusterManager: ClusterManager<LocationItem>? = null

    private lateinit var viewModel: SocialViewModel

    private lateinit var mMap: GoogleMap

    override fun getLayoutRes() = com.joerakhimov.smartexpenses.R.layout.fragment_social

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel =
            ViewModelProviders.of(this).get(SocialViewModel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager
            .findFragmentById(com.joerakhimov.smartexpenses.R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        observeToastMessage()
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

    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap

        val center = LatLng(47.497, 19.050)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(center, 13F))

//        mClusterManager = ClusterManager(context, mMap)
//        mMap.setOnMarkerClickListener(mClusterManager)

        viewModel.locations.observe(this, Observer {
            mMap.clear()
            it.forEach {
                mMap.addMarker(
                    MarkerOptions().position(
                        LatLng(
                            it?.latitude!!,
                            it?.longitude!!
                        )
                    ).title(it.title))
               }
            }
        )

    }

}