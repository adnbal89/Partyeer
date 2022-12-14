package com.partyeer.data.remote.store.party.mapper

import com.partyeer.data.base.mapper.BaseMapper
import com.partyeer.data.remote.store.party.remote.model.PartyDTO
import com.partyeer.domain.repository.party.model.Party
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PartyDTOMapper @Inject constructor(
    val conceptDTOMapper: ConceptDTOMapper,
    val pictureDTOMapper: PictureDTOMapper,
    val addressDTOMapper: AddressDTOMapper,
    val partyCreatorUserDTOMapper: PartyCreatorUserDTOMapper
) : BaseMapper<Party, PartyDTO> {

    override fun map(source: Party, vararg extra: Any?): PartyDTO {
        return PartyDTO(
            id = source.id,
            logoUrl = source.logoUrl,
            title = source.title,
            concept = conceptDTOMapper.map(source.concept),
            timeStart = source.timeStart,
            timeEnd = source.timeEnd,
            description = source.description,
            entranceFee = source.entranceFee,
            pictures = pictureDTOMapper.map(source.pictures).toMutableList(),
            likeCount = source.likeCount,
            inviteeMap = source.inviteeMap,
            likedUserIdMap = source.likedUserIdMap,
            appliedUserIdMap = source.appliedUserIdMap,
            address = addressDTOMapper.map(source.address),
            tagMap = source.tagMap,
            partyCreatorUser = partyCreatorUserDTOMapper.map(source.partyCreatorUser)
        )
    }
}