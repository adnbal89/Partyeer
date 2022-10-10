package com.partyeer.domain.repository.party.model

import com.partyeer.domain.repository.base.model.BaseItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: String = "",
    val userName: String = "",
    val name: String = "",
    val surname: String = "",
    val dateOfBirth: Long,
    val signUpDate: Long,
    val createdPartyIdList: MutableList<String>,
    val profilePicture: Picture,
    var invitedPartyIdList: MutableList<String>,
    var appliedPartyIdList: MutableList<String>,
    var followerUserIdList: MutableList<String>,
    var followingUserIdList: MutableList<String>,
    var favoritePartyIdList: MutableList<String>,

    ) : BaseItem()
