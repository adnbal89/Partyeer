package com.partyeer.data.remote.store.party.mapper

import com.partyeer.data.base.mapper.BaseMapper
import com.partyeer.data.remote.store.party.remote.model.UserDTO
import com.partyeer.domain.repository.party.model.User
import javax.inject.Inject

class UserMapper @Inject constructor(
    val pictureMapper: PictureMapper
) : BaseMapper<UserDTO, User> {
    override fun map(source: UserDTO, vararg extra: Any?): User {
        return User(
            id = source.id,
            userName = source.userName,
            name = source.name,
            surname = source.surname,
            dateOfBirth = source.dateOfBirth,
            signUpDate = source.signUpDate,
            createdPartyIdList = source.createdPartyIdList,
            profilePicture = pictureMapper.map(source.profilePicture),
            invitedPartyIdList = source.invitedPartyIdList,
            appliedPartyIdList = source.appliedPartyIdList,
            followerUserIdList = source.followerUserIdList,
            followingUserIdList = source.followingUserIdList,
            favoritePartyIdList = source.favoritePartyIdList,
        )
    }
}