package com.gg.petdoc_project.Frag

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gg.petdoc_project.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class map_Frag : Fragment(),OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView:View = inflater.inflate(R.layout.fragment_map_,container,false)
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapview) as SupportMapFragment
        mapFragment.getMapAsync(this)
        // Inflate the layout for this fragment
        return rootView
    }

    override fun onMapReady(p0: GoogleMap) {
        mMap =p0
        val marker = LatLng(37.50463,127.02248)
        mMap.addMarker(MarkerOptions().position(marker).title("서울특별시 서초구 강남대로79길 32 (반포동) 3층 사무실"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 15F))
    }

}