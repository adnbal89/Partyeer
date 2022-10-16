package com.partyeer.presentation.ui.main.features.party.googlemaps

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.partyeer.presentation.databinding.MarkerInfoContentsBinding
import com.partyeer.presentation.ui.main.features.party.googlemaps.model.PartyMapItem

class MarkerInfoWindowAdapter(
    private val context: Context
) : GoogleMap.InfoWindowAdapter {

    override fun getInfoContents(marker: Marker): View? {
        //get Tag
        val partyMapItem = marker.tag as? PartyMapItem ?: return null
        val view = MarkerInfoContentsBinding.inflate(LayoutInflater.from(context))
        view.textViewTitle.text = partyMapItem.title
        view.textViewAddress.text = partyMapItem.address

        return view.root

    }

    override fun getInfoWindow(marker: Marker): View? {
        // Return null to indicate that the
        // default window (white bubble) should be used
        return null
    }

}