package com.partyeer.domain.repository.party.model

import android.location.Location
import com.partyeer.domain.repository.base.model.BaseItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class Party(
    val id: String
) : BaseItem() {
    constructor(
        id: String,
        logoUrl: String?,
        title: String,
        concept: String?,
        locationInfo: Location,
        timeStart: Double,
        timeEnd: Double,
        description: String?
    ) : this(id)
}

