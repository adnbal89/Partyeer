package com.partyeer.domain.repository.party.model

import android.location.Location
import com.partyeer.domain.repository.model.BaseItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class Party(
    val id: String,
    val logoUrl: String?,
    val title: String,
    val concept: String?,
    val locationInfo: Location,
    val timeStart: Double,
    val timeEnd: Double,
    val description: String?
) : BaseItem()