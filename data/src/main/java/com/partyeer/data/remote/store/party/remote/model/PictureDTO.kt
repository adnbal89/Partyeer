package com.partyeer.data.remote.store.party.remote.model

import com.partyeer.data.base.remote.model.BaseResponse

data class PictureDTO(
    val preview: String = "",
    val fullSize: String = "",
) : BaseResponse()