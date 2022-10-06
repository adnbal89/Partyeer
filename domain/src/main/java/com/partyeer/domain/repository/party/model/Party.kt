package com.partyeer.domain.repository.party.model

import android.location.Location
import com.partyeer.domain.repository.base.model.BaseItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class Party(
    val id: String,
    val logoUrl: String?,
    val title: String,
    val concept: Concept,
    val timeStart: Double,
    val pictures: MutableList<Picture>,
    var likeCount: Int = 0

) : BaseItem() {
    constructor(
        id: String,
        logoUrl: String?,
        title: String,
        concept: Concept,
        locationInfo: Location,
        timeStart: Double,
        timeEnd: Double,
        description: String?,
        pictures: MutableList<Picture>,
        likeCount: Int
    ) : this(id, logoUrl, title, concept, timeStart, pictures, likeCount)
}

