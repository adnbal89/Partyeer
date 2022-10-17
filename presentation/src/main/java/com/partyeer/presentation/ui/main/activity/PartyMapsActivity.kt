package com.partyeer.presentation.ui.main.activity

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.ktx.awaitMap
import com.partyeer.presentation.R
import com.partyeer.presentation.ui.main.base.BaseActivity
import com.partyeer.presentation.ui.main.features.party.googlemaps.MarkerInfoWindowAdapter
import com.partyeer.presentation.ui.main.features.party.googlemaps.PartyMapItemRenderer
import com.partyeer.presentation.ui.main.features.party.googlemaps.model.PartyMapItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PartyMapsActivity : BaseActivity(), OnMapReadyCallback {
    //private var partyList: ArrayList<Party> = ArrayList<Party>()
    private lateinit var partyList: List<PartyMapItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_party_maps)

        // Get the SupportMapFragment and request notification when the map is ready to be used.
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        lifecycleScope.launchWhenCreated {
            mapFragment?.awaitMap()
        }

        mapFragment?.getMapAsync(this)
        //get partyList from previous activity
        partyList = intent.getParcelableArrayListExtra<PartyMapItem>("partyList").let { it!! }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val bounds = LatLngBounds.builder()

        partyList.forEach {
            bounds.include(it.latLng)
        }
        googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 10))

        addClusteredMarkers(googleMap)
        //googleMap.moveCamera(CameraUpdateFactory.newLatLng(bursa))
    }

    private fun addClusteredMarkers(googleMap: GoogleMap) {
        val clusterManager = ClusterManager<PartyMapItem>(this, googleMap)
        clusterManager.renderer =
            PartyMapItemRenderer(
                this,
                googleMap,
                clusterManager
            )

        clusterManager.markerCollection
            .setInfoWindowAdapter(MarkerInfoWindowAdapter(this))

        clusterManager.addItems(partyList)
        clusterManager.cluster()

        googleMap.setOnCameraIdleListener {
            clusterManager.markerCollection.markers.forEach {
                it.alpha = 1.0f
            }
            clusterManager.clusterMarkerCollection.markers.forEach {
                it.alpha = 1.0f
            }
            clusterManager.onCameraIdle()
        }

        googleMap.setOnCameraMoveStartedListener {
            clusterManager.markerCollection.markers.forEach {
                it.alpha = 0.3f
            }
            clusterManager.clusterMarkerCollection.markers.forEach {
                it.alpha = 0.3f
            }
        }
    }
}