package com.partyeer.data.local.store.party.mapper

import com.partyeer.data.base.mapper.BaseMapper
import com.partyeer.data.local.model.ConceptEntity
import com.partyeer.domain.repository.party.model.Concept

class ConceptEntityMapper : BaseMapper<ConceptEntity, Concept> {
    override fun map(source: ConceptEntity, vararg extra: Any?): Concept {
        return Concept(
            description = source.description
        )
    }
}