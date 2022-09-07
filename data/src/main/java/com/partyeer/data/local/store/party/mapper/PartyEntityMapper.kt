package com.partyeer.data.local.store.party.mapper

import com.partyeer.data.base.mapper.BaseMapper
import com.partyeer.data.local.model.PartyEntity
import com.partyeer.domain.repository.party.model.Party

class PartyEntityMapper : BaseMapper<PartyEntity, Party> {
    override fun map(source: PartyEntity, vararg extra: Any?): Party {
        return Party(
            id = source.id
        )
    }
}