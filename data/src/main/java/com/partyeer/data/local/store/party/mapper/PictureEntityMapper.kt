package com.partyeer.data.local.store.party.mapper

import com.partyeer.data.base.mapper.BaseMapper
import com.partyeer.data.local.model.PictureEntity
import com.partyeer.domain.repository.party.model.Picture

class PictureEntityMapper : BaseMapper<PictureEntity, Picture> {

    override fun map(source: PictureEntity, vararg extra: Any?): Picture {
        return Picture(
            preview = source.preview,
            fullSize = source.fullSize
        )
    }
}