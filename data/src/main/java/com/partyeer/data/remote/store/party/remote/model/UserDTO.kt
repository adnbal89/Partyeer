package com.partyeer.data.remote.store.party.remote.model

data class UserDTO(
    val id: String = "",
    val userName: String = "",
    val name: String = "",
    val surname: String = "",
    val dateOfBirth: Long = 0,
    val signUpDate: Long = 0,
    val createdPartyIdList: MutableList<String>,
    val profilePicture: PictureDTO = PictureDTO("", ""),
    var invitedPartyIdList: MutableList<String>,
    var appliedPartyIdList: MutableList<String>,
    var followerUserIdList: MutableList<String>,
    var followingUserIdList: MutableList<String>,
    var favoritePartyIdList: MutableList<String>,
)
