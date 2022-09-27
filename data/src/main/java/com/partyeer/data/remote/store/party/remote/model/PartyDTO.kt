package com.partyeer.data.remote.store.party.remote.model

import com.partyeer.data.base.remote.model.BaseResponse
import com.partyeer.domain.repository.party.model.Picture

data class PartyDTO(
    val id: String = "",
    val logoUrl: String? = "",
    val title: String = "",
    val concept: String? = "",
    val timeStart: Double = 0.0,
    val pictures: MutableList<Picture> = arrayListOf()
) : BaseResponse()