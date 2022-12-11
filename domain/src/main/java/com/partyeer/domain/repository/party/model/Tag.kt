package com.partyeer.domain.repository.party.model

import com.partyeer.domain.repository.base.model.BaseItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class Tag(
    val title: String = "",
    val parties: Map<String, Party> = hashMapOf()
) : BaseItem()