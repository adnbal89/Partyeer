package com.partyeer.data.remote.store.party.mapper

import com.partyeer.data.base.mapper.BaseMapper
import com.partyeer.data.remote.store.party.remote.model.TagDTO
import com.partyeer.domain.repository.party.model.Tag
import javax.inject.Inject

class TagMapper @Inject constructor(
    private val partyMapper: PartyMapper,
) : BaseMapper<TagDTO, Tag> {
    override fun map(source: TagDTO, vararg extra: Any?): Tag {
        return Tag(
            title = source.title,
            parties = source.parties.mapValues {
                partyMapper.map(it.value)
            }
        )
    }
}