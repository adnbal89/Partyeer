package com.partyeer.data.remote.store.party.mapper

import com.partyeer.data.base.mapper.BaseMapper
import com.partyeer.data.remote.store.user.remote.model.PartyCreatorUserDTO
import com.partyeer.domain.repository.user.model.PartyCreatorUser
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PartyCreatorUserMapper @Inject constructor(
    private val pictureMapper: PictureMapper
) : BaseMapper<PartyCreatorUserDTO, PartyCreatorUser> {

    override fun map(source: PartyCreatorUserDTO, vararg extra: Any?): PartyCreatorUser {
        return PartyCreatorUser(
            id = source.id,
            userName = source.userName,
            profilePicture = pictureMapper.map(source.profilePicture)
        )
    }
}