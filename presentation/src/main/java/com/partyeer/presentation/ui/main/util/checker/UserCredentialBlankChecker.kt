package com.partyeer.presentation.ui.main.util.checker

import com.partyeer.domain.repository.login.model.UserCredential

object UserCredentialBlankChecker : UserCredentialChecker {

    override fun check(userCredential: UserCredential): Boolean {
        return userCredential.userMail.isNullOrBlank().not() && userCredential.password.isNullOrBlank()
            .not()
    }
}