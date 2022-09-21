package com.partyeer.data.local.model

import com.partyeer.data.base.local.model.BaseEntity

data class PartyEntity(
    val id: String,
    val logoUrl: String?,
    val title: String,
    val concept: String?,
    val timeStart: Double
) : BaseEntity()
