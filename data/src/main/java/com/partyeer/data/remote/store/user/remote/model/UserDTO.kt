package com.partyeer.data.remote.store.user.remote.model

import com.partyeer.data.base.remote.model.BaseResponse
import com.partyeer.data.remote.store.party.remote.model.PictureDTO

data class UserDTO(
    val id: String = "",
    val userName: String = "",
    val name: String = "",
    val surname: String = "",
    val dateOfBirth: Long = 0,
    val signUpDate: Long = 0,
    val createdPartyIdMap: HashMap<String, Boolean> = HashMap(),
    val profilePicture: PictureDTO = PictureDTO(),
    var invitedPartyIdMap: HashMap<String, Boolean> = HashMap(),
    var appliedPartyIdMap: HashMap<String, Boolean> = HashMap(),
    var followerUserIdMap: HashMap<String, Boolean> = HashMap(),
    var followingUserIdMap: HashMap<String, Boolean> = HashMap(),
    var favoritePartyIdMap: HashMap<String, Boolean> = HashMap()
) : BaseResponse()
