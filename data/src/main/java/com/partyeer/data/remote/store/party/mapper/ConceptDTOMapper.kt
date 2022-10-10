package com.partyeer.data.remote.store.party.mapper

import com.partyeer.data.base.mapper.BaseMapper
import com.partyeer.data.remote.store.party.remote.model.ConceptDTO
import com.partyeer.domain.repository.party.model.Concept

class ConceptDTOMapper : BaseMapper<Concept, ConceptDTO> {
    override fun map(source: Concept, vararg extra: Any?): ConceptDTO {
        return ConceptDTO(
            description = source.description
        )
    }
}