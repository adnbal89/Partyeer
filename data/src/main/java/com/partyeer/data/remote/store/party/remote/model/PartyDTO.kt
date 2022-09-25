package com.partyeer.data.remote.store.party.remote.model

import android.net.Uri
import com.partyeer.data.base.remote.model.BaseResponse

data class PartyDTO(
    val id: String = "",
    val logoUrl: String? = "",
    val title: String = "",
    val concept: String? = "",
    val timeStart: Double = 0.0,
    val pictures: List<Uri> = arrayListOf()
) : BaseResponse()