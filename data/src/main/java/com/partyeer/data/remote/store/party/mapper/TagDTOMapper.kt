package com.partyeer.data.remote.store.party.mapper

import com.partyeer.data.base.mapper.BaseMapper
import com.partyeer.data.remote.store.party.remote.model.TagDTO
import com.partyeer.domain.repository.party.model.Tag
import javax.inject.Inject

class TagDTOMapper @Inject constructor(
    private val partyDTOMapper: PartyDTOMapper,
) : BaseMapper<Tag, TagDTO> {
    override fun map(source: Tag, vararg extra: Any?): TagDTO {
        return TagDTO(
            title = source.title,
            parties = source.parties.mapValues {
                partyDTOMapper.map(it.value)
            }
        )
    }
}