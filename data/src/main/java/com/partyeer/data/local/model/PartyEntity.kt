package com.partyeer.data.local.model

import com.partyeer.data.base.local.model.BaseEntity
import com.partyeer.domain.repository.party.model.Picture

data class PartyEntity(
    val id: String,
    val logoUrl: String?,
    val title: String,
    val concept: ConceptEntity,
    val timeStart: Double,
    val pictures: MutableList<Picture>,
    var likeCount: Int = 0
) : BaseEntity()
