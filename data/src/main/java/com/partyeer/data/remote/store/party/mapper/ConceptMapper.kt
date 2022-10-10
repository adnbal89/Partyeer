package com.partyeer.data.remote.store.party.mapper

import com.partyeer.data.base.mapper.BaseMapper
import com.partyeer.data.remote.store.party.remote.model.ConceptDTO
import com.partyeer.domain.repository.party.model.Concept

class ConceptMapper : BaseMapper<ConceptDTO, Concept> {
    override fun map(source: ConceptDTO, vararg extra: Any?): Concept {
        return Concept(
            description = source.description
        )
    }
}