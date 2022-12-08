package com.partyeer.data.local.model

import com.partyeer.data.base.remote.model.BaseResponse

class PartyCreatorUserEntity(
    val id: String = "",
    val userName: String = "",
    val profilePicture: PictureEntity = PictureEntity()
) : BaseResponse()

