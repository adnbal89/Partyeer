package com.partyeer.presentation.ui.main.features.party.googlemaps

import android.content.Context
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer
import com.partyeer.presentation.ui.main.features.party.googlemaps.model.PartyMapItem

/**
 * A custom cluster renderer for [PartyMapItem] objects.
 */
class PartyMapItemRenderer(
    private val context: Context,
    map: GoogleMap,
    clusterManager: ClusterManager<PartyMapItem>
) : DefaultClusterRenderer<PartyMapItem>(context, map, clusterManager) {

    override fun onBeforeClusterItemRendered(
        item: PartyMapItem,
        markerOptions: MarkerOptions
    ) {
        markerOptions.title(item.title).position(item.latLng)
    }

    override fun onClusterItemRendered(clusterItem: PartyMapItem, marker: Marker) {
        marker?.tag = clusterItem
    }
}