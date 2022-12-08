package com.partyeer.data.remote.store.user.remote.model

import com.partyeer.data.base.remote.model.BaseResponse
import com.partyeer.data.remote.store.party.remote.model.PictureDTO

class PartyCreatorUserDTO(
    val id: String = "",
    val userName: String = "",
    val profilePicture: PictureDTO = PictureDTO()
) : BaseResponse()

