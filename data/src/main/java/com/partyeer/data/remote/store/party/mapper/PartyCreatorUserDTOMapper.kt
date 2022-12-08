package com.partyeer.data.remote.store.party.mapper

import com.partyeer.data.base.mapper.BaseMapper
import com.partyeer.data.remote.store.user.remote.model.PartyCreatorUserDTO
import com.partyeer.domain.repository.user.model.PartyCreatorUser
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PartyCreatorUserDTOMapper @Inject constructor(
    private val pictureDTOMapper: PictureDTOMapper,
) : BaseMapper<PartyCreatorUser, PartyCreatorUserDTO> {

    override fun map(source: PartyCreatorUser, vararg extra: Any?): PartyCreatorUserDTO {
        return PartyCreatorUserDTO(
            id = source.id,
            userName = source.userName,
            profilePicture = pictureDTOMapper.map(source.profilePicture)
        )
    }
}