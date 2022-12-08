package com.partyeer.data.remote.store.party.mapper

import com.partyeer.data.base.mapper.BaseMapper
import com.partyeer.data.remote.store.party.remote.model.PartyDTO
import com.partyeer.domain.repository.party.model.Party
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PartyMapper @Inject constructor(
    private val conceptMapper: ConceptMapper,
    private val pictureMapper: PictureMapper,
    private val addressMapper: AddressMapper,
    private val partyCreatorUserMapper: PartyCreatorUserMapper
) : BaseMapper<PartyDTO, Party> {

    override fun map(source: PartyDTO, vararg extra: Any?): Party {
        return Party(
            id = source.id,
            logoUrl = source.logoUrl,
            title = source.title,
            concept = conceptMapper.map(source.concept),
            timeStart = source.timeStart,
            timeEnd = source.timeEnd,
            description = source.description,
            entranceFee = source.entranceFee,
            pictures = pictureMapper.map(source.pictures).toMutableList(),
            likeCount = source.likeCount,
            inviteeMap = source.inviteeMap,
            likedUserIdMap = source.likedUserIdMap,
            appliedUserIdMap = source.appliedUserIdMap,
            address = addressMapper.map(source.address),
            tagMap = source.tagMap,
            partyCreatorUser = partyCreatorUserMapper.map(source.partyCreatorUser)
        )
    }

}