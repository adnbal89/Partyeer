package com.partyeer.data.remote.store.party.mapper

import com.partyeer.data.base.mapper.BaseMapper
import com.partyeer.data.remote.store.party.remote.model.ConceptDTO
import com.partyeer.data.remote.store.party.remote.model.PartyDTO
import com.partyeer.domain.repository.party.model.Party
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PartyDTOMapper @Inject constructor() : BaseMapper<Party, PartyDTO> {

    override fun map(source: Party, vararg extra: Any?): PartyDTO {
        return PartyDTO(
            id = source.id,
            title = source.title,
            logoUrl = source.logoUrl,
            concept = ConceptDTO(source.concept.description),
            timeStart = source.timeStart,
            pictures = source.pictures,
            likeCount = source.likeCount
        )
    }
}