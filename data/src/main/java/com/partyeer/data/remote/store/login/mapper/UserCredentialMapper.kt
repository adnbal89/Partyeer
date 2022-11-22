package com.partyeer.data.remote.store.login.mapper

import com.partyeer.data.base.mapper.BaseMapper
import com.partyeer.data.remote.store.login.model.UserCredentialDTO
import com.partyeer.domain.repository.login.model.UserCredential

class UserCredentialMapper : BaseMapper<UserCredentialDTO, UserCredential> {
    override fun map(source: UserCredentialDTO, vararg extra: Any?): UserCredential {
        return UserCredential(
            userMail = source.userMail,
            password = source.password
        )
    }
}