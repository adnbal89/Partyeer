package com.partyeer.data.local.store.party.mapper

import com.partyeer.data.base.mapper.BaseMapper
import com.partyeer.data.local.model.UserEntity
import com.partyeer.domain.repository.user.model.User
import javax.inject.Inject

class UserEntityMapper @Inject constructor(
    val pictureEntityMapper: PictureEntityMapper
) : BaseMapper<UserEntity, User> {
    override fun map(source: UserEntity, vararg extra: Any?): User {
        return User(
            id = source.id,
            userName = source.userName,
            name = source.name,
            surname = source.surname,
            dateOfBirth = source.dateOfBirth,
            signUpDate = source.signUpDate,
            createdPartyIdMap = source.createdPartyIdMap,
            profilePicture = pictureEntityMapper.map(source.profilePicture),
            invitedPartyIdMap = source.invitedPartyIdMap,
            appliedPartyIdMap = source.appliedPartyIdMap,
            followerUserIdMap = source.followerUserIdMap,
            followingUserIdMap = source.followingUserIdMap,
            favoritePartyIdMap = source.favoritePartyIdMap,
        )
    }
}