package com.partyeer.data.local.model

import com.partyeer.data.base.local.model.BaseEntity

data class UserEntity(
    val id: String = "",
    val userName: String = "",
    val name: String = "",
    val surname: String = "",
    val dateOfBirth: Long,
    val signUpDate: Long,
    val createdPartyIdList: MutableList<String>,
    val profilePicture: PictureEntity,
    var invitedPartyIdList: MutableList<String>,
    var appliedPartyIdList: MutableList<String>,
    var followerUserIdList: MutableList<String>,
    var followingUserIdList: MutableList<String>,
    var favoritePartyIdList: MutableList<String>,
) : BaseEntity()