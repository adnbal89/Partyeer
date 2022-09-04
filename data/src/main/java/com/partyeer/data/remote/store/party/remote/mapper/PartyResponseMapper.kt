package com.partyeer.data.remote.store.party.remote.mapper

import com.partyeer.data.remote.store.party.base.remote.mapper.BaseMapper
import com.partyeer.data.remote.store.party.remote.model.PartyResponse
import com.partyeer.domain.repository.party.model.Party
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PartyResponseMapper @Inject constructor() : BaseMapper<PartyResponse, Party> {
    override fun map(source: PartyResponse, vararg extra: Any?): Party {
        return Party(
            id = source.id
        )
    }
}