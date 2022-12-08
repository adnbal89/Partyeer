package com.partyeer.domain.repository.user.model

import com.partyeer.domain.repository.base.model.BaseItem
import com.partyeer.domain.repository.party.model.Picture
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: String = "",
    val userName: String = "",
    val name: String = "",
    val surname: String = "",
    val dateOfBirth: Long,
    val signUpDate: Long,
    val createdPartyIdMap: HashMap<String, Boolean> = HashMap(),
    val profilePicture: Picture,
    var invitedPartyIdMap: HashMap<String, Boolean> = HashMap(),
    var appliedPartyIdMap: HashMap<String, Boolean> = HashMap(),
    var followerUserIdMap: HashMap<String, Boolean> = HashMap(),
    var followingUserIdMap: HashMap<String, Boolean> = HashMap(),
    var favoritePartyIdMap: HashMap<String, Boolean> = HashMap(),
) : BaseItem() {
    val fullName: String
        get() = "$name $surname"
}

