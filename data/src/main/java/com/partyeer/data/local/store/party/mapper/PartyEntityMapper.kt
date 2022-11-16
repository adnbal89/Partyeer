package com.partyeer.data.local.store.party.mapper

import com.partyeer.data.base.mapper.BaseMapper
import com.partyeer.data.local.model.PartyEntity
import com.partyeer.domain.repository.party.model.Party

class PartyEntityMapper : BaseMapper<PartyEntity, Party> {
    private val conceptMapper: ConceptEntityMapper = ConceptEntityMapper()
    private val pictureMapper: PictureEntityMapper = PictureEntityMapper()

    override fun map(source: PartyEntity, vararg extra: Any?): Party {
        return Party(
            id = source.id,
            logoUrl = source.logoUrl,
            title = source.title,
            concept = conceptMapper.map(source.concept),
            longitude = source.longitude,
            latitude = source.latitude,
            timeStart = source.timeStart,
            timeEnd = source.timeEnd,
            entranceFee = source.entranceFee,
            description = source.description,
            pictures = pictureMapper.map(source.pictures).toMutableList(),
            likeCount = source.likeCount,
            inviteeList = source.inviteeList,
            likedUserIdList = source.likedUserIdList,
            appliedUserIdList = source.appliedUserIdList,
            creatorUserId = source.creatorUserId,
            tagList = source.tagList
        )
    }
}