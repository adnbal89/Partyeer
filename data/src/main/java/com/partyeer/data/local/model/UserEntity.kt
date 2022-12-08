package com.partyeer.data.local.model

import com.partyeer.data.base.local.model.BaseEntity

data class UserEntity(
    val id: String = "",
    val userName: String = "",
    val name: String = "",
    val surname: String = "",
    val dateOfBirth: Long,
    val signUpDate: Long,
    val createdPartyIdMap: HashMap<String, Boolean> = HashMap(),
    val profilePicture: PictureEntity,
    var invitedPartyIdMap: HashMap<String, Boolean> = HashMap(),
    var appliedPartyIdMap: HashMap<String, Boolean> = HashMap(),
    var followerUserIdMap: HashMap<String, Boolean> = HashMap(),
    var followingUserIdMap: HashMap<String, Boolean> = HashMap(),
    var favoritePartyIdMap: HashMap<String, Boolean> = HashMap(),
) : BaseEntity()