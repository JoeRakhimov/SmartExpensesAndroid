package com.joerakhimov.smartexpenses.screen.main.social

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.joerakhimov.smartexpenses.R
import com.joerakhimov.smartexpenses.base.BaseFragment

class SocialFragment : BaseFragment(), OnMapReadyCallback {

    private lateinit var notificationsViewModel: SocialViewModel

    private lateinit var mMap: GoogleMap

    override fun getLayoutRes() = R.layout.fragment_social

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        notificationsViewModel =
            ViewModelProviders.of(this).get(SocialViewModel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap

        mMap.addMarker(MarkerOptions().position(LatLng(47.480, 19.070)).title("Expense #1"))
        mMap.addMarker(MarkerOptions().position(LatLng(47.493, 19.065)).title("Expense #2"))
        mMap.addMarker(MarkerOptions().position(LatLng(47.489, 19.060)).title("Expense #3"))
        mMap.addMarker(MarkerOptions().position(LatLng(47.499, 19.055)).title("Expense #4"))
        mMap.addMarker(MarkerOptions().position(LatLng(47.490, 19.050)).title("Expense #5"))
        mMap.addMarker(MarkerOptions().position(LatLng(47.485, 19.045)).title("Expense #7"))
        mMap.addMarker(MarkerOptions().position(LatLng(47.481, 19.078)).title("Expense #7"))
        mMap.addMarker(MarkerOptions().position(LatLng(47.494, 19.043)).title("Expense #8"))
        mMap.addMarker(MarkerOptions().position(LatLng(47.499, 19.040)).title("Expense #9"))
        mMap.addMarker(MarkerOptions().position(LatLng(47.482, 19.056)).title("Expense #10"))

        val center = LatLng(47.497, 19.050)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(center, 13F))

    }

}