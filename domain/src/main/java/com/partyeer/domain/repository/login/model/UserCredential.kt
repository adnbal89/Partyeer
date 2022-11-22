package com.partyeer.domain.repository.login.model

import com.partyeer.domain.repository.base.model.BaseItem
import kotlinx.parcelize.Parcelize

@Parcelize
class UserCredential(
    val userMail: String,
    val password: String,
) : BaseItem() {
}