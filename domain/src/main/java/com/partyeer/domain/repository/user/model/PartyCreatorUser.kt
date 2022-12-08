package com.partyeer.domain.repository.user.model

import com.partyeer.domain.repository.base.model.BaseItem
import com.partyeer.domain.repository.party.model.Picture
import kotlinx.parcelize.Parcelize

@Parcelize
class PartyCreatorUser(
    val id: String = "",
    val userName: String = "",
    val profilePicture: Picture = Picture()
) : BaseItem()

