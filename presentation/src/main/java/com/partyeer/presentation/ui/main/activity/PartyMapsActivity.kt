package com.partyeer.presentation.ui.main.activity

import android.os.Bundle
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.partyeer.domain.repository.party.model.Party
import com.partyeer.presentation.R
import com.partyeer.presentation.ui.main.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PartyMapsActivity : BaseActivity(), OnMapReadyCallback {
    private var partyList: ArrayList<Party> = ArrayList<Party>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_party_maps)

        // Get the SupportMapFragment and request notification when the map is ready to be used.
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
        //get partyList from previous activity
        partyList = intent.getParcelableArrayListExtra<Party>("partyList").let { it!! }
    }


    override fun onMapReady(googleMap: GoogleMap) {
        val bursa = LatLng(40.255473, 28.995557)
        partyList.forEach {
            val location = LatLng(it.latitude, it.longitude)

            googleMap.addMarker(
                MarkerOptions().position(location)
                    .title(it.title)
            )
        }

        //googleMap.moveCamera(CameraUpdateFactory.newLatLng(bursa))
    }

}