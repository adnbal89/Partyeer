package com.partyeer.data.remote.store.party.remote.model

import com.partyeer.data.base.remote.model.BaseResponse

data class TagDTO(
    val title: String = "",
    val parties: Map<String, PartyDTO> = hashMapOf()
) : BaseResponse()