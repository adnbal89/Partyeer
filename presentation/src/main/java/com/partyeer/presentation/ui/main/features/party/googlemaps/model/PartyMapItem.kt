package com.partyeer.presentation.ui.main.features.party.googlemaps.model

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem
import com.partyeer.domain.repository.base.model.BaseItem
import kotlinx.parcelize.Parcelize


@Parcelize
class PartyMapItem(
    val partyTitle: String,
    val latLng: LatLng,
    val address: String,
    //val rating: Float
) : ClusterItem, BaseItem() {
    override fun getPosition(): LatLng =
        latLng

    override fun getTitle(): String =
        partyTitle

    override fun getSnippet(): String =
        address
}