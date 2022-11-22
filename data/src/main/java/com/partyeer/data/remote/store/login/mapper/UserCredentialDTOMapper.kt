package com.partyeer.data.remote.store.login.mapper

import com.partyeer.data.base.mapper.BaseMapper
import com.partyeer.data.remote.store.login.model.UserCredentialDTO
import com.partyeer.domain.repository.login.model.UserCredential

class UserCredentialDTOMapper : BaseMapper<UserCredential, UserCredentialDTO> {
    override fun map(source: UserCredential, vararg extra: Any?): UserCredentialDTO {
        return UserCredentialDTO(
            userMail = source.userMail,
            password = source.password
        )
    }
}