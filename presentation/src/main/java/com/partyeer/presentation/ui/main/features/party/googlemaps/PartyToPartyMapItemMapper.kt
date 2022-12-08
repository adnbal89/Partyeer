package com.partyeer.presentation.ui.main.features.party.googlemaps

import com.google.android.gms.maps.model.LatLng
import com.partyeer.domain.repository.party.model.Party
import com.partyeer.presentation.ui.main.base.BaseMapper
import com.partyeer.presentation.ui.main.features.party.googlemaps.model.PartyMapItem

class PartyToPartyMapItemMapper : BaseMapper<Party, PartyMapItem> {

    override fun map(source: Party, vararg extra: Any?): PartyMapItem {
        return PartyMapItem(
            partyTitle = source.title,
            latLng = LatLng(source.address.latitude, source.address.longitude),
            address = source.address.addressLine ?: ""
        )
    }
}