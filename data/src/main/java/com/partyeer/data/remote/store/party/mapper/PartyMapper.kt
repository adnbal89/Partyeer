package com.partyeer.data.remote.store.party.mapper

import com.partyeer.data.base.mapper.BaseMapper
import com.partyeer.data.remote.store.party.remote.model.PartyDTO
import com.partyeer.domain.repository.party.model.Party
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PartyMapper @Inject constructor() : BaseMapper<PartyDTO, Party> {

    override fun map(source: PartyDTO, vararg extra: Any?): Party {
        return Party(
            id = source.id,
            title = source.title,
            logoUrl = source.logoUrl,
            concept = source.concept,
            timeStart = source.timeStart
        )
    }
}