package com.partyeer.domain.repository.party.model

import android.location.Location
import android.net.Uri
import com.partyeer.domain.repository.base.model.BaseItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class Party(
    val id: String,
    val logoUrl: String?,
    val title: String,
    val concept: String?,
    val timeStart: Double,
    val pictures: List<Uri>,

    ) : BaseItem() {
    constructor(
        id: String,
        logoUrl: String?,
        title: String,
        concept: String?,
        locationInfo: Location,
        timeStart: Double,
        timeEnd: Double,
        description: String?,
        pictures: List<Uri>,
    ) : this(id, logoUrl, title, concept, timeStart, pictures)
}

