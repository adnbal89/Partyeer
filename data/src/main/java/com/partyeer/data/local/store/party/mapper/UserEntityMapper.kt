package com.partyeer.data.local.store.party.mapper

import com.partyeer.data.base.mapper.BaseMapper
import com.partyeer.data.local.model.UserEntity
import com.partyeer.domain.repository.party.model.User
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
            createdPartyIdList = source.createdPartyIdList,
            profilePicture = pictureEntityMapper.map(source.profilePicture),
            invitedPartyIdList = source.invitedPartyIdList,
            appliedPartyIdList = source.appliedPartyIdList,
            followerUserIdList = source.followerUserIdList,
            followingUserIdList = source.followingUserIdList,
            favoritePartyIdList = source.favoritePartyIdList,
        )
    }
}