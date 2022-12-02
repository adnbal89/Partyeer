package com.partyeer.data.remote.store.user.mapper

import com.partyeer.data.base.mapper.BaseMapper
import com.partyeer.data.remote.store.party.mapper.PictureDTOMapper
import com.partyeer.data.remote.store.user.remote.model.UserDTO
import com.partyeer.domain.repository.user.model.User
import javax.inject.Inject

class UserDTOMapper @Inject constructor(
    val pictureDTOMapper: PictureDTOMapper
) : BaseMapper<User, UserDTO> {
    override fun map(source: User, vararg extra: Any?): UserDTO {
        return UserDTO(
            id = source.id,
            userName = source.userName,
            name = source.name,
            surname = source.surname,
            dateOfBirth = source.dateOfBirth,
            signUpDate = source.signUpDate,
            createdPartyIdMap = source.createdPartyIdMap,
            profilePicture = pictureDTOMapper.map(source.profilePicture),
            invitedPartyIdMap = source.invitedPartyIdMap,
            appliedPartyIdMap = source.appliedPartyIdMap,
            followerUserIdMap = source.followerUserIdMap,
            followingUserIdMap = source.followingUserIdMap,
            favoritePartyIdMap = source.favoritePartyIdMap,
        )
    }
}