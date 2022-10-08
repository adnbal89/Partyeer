package com.partyeer.domain.repository.party.model

import com.partyeer.domain.repository.base.model.BaseItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: String = "",
    val userName: String = "",
    val name: String = "",
    val surname: String = "",
    val profilePicture: Picture,

    ) : BaseItem()

