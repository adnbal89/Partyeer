package com.partyeer.data.remote.store.party.mapper

import com.partyeer.data.base.mapper.BaseMapper
import com.partyeer.data.remote.store.party.remote.model.PictureDTO
import com.partyeer.domain.repository.party.model.Picture

class PictureDTOMapper : BaseMapper<Picture, PictureDTO> {

    override fun map(source: Picture, vararg extra: Any?): PictureDTO {
        return PictureDTO(
            preview = source.preview,
            fullSize = source.fullSize
        )
    }
}