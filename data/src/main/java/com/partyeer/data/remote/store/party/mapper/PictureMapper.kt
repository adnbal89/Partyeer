package com.partyeer.data.remote.store.party.mapper

import com.partyeer.data.base.mapper.BaseMapper
import com.partyeer.data.remote.store.party.remote.model.PictureDTO
import com.partyeer.domain.repository.party.model.Picture

class PictureMapper : BaseMapper<PictureDTO, Picture> {

    override fun map(source: PictureDTO, vararg extra: Any?): Picture {
        return Picture(
            preview = source.preview,
            fullSize = source.fullSize
        )
    }
}