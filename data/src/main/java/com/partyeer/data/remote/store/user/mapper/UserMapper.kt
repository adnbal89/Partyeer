package com.partyeer.data.remote.store.user.mapper

import com.partyeer.data.base.mapper.BaseMapper
import com.partyeer.data.remote.store.party.mapper.PictureMapper
import com.partyeer.data.remote.store.user.remote.model.UserDTO
import com.partyeer.domain.repository.user.model.User
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
            createdPartyIdMap = source.createdPartyIdMap,
            profilePicture = pictureMapper.map(source.profilePicture),
            invitedPartyIdMap = source.invitedPartyIdMap,
            appliedPartyIdMap = source.appliedPartyIdMap,
            followerUserIdMap = source.followerUserIdMap,
            followingUserIdMap = source.followingUserIdMap,
            favoritePartyIdMap = source.favoritePartyIdMap,
        )
    }
}