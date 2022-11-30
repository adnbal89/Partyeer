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
            inviteeList = source.inviteeList,
            likedUserIdList = source.likedUserIdList,
            appliedUserIdList = source.appliedUserIdList,
            creatorUserId = source.creatorUserId,
            address = addressMapper.map(source.address),
            tagList = source.tagList
        )
    }

}