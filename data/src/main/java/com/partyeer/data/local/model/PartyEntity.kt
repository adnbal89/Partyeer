package com.partyeer.data.local.model

import android.net.Uri
import com.partyeer.data.base.local.model.BaseEntity
import com.partyeer.domain.repository.party.model.Picture

data class PartyEntity(
    val id: String,
    val logoUrl: String?,
    val title: String,
    val concept: String?,
    val timeStart: Double,
    val pictures: List<Uri>
) : BaseEntity()
