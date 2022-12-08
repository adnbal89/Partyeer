package com.partyeer.presentation.ui.main.util.checker

import com.partyeer.domain.repository.login.model.UserCredential

object UserCredentialValidChecker : UserCredentialChecker {

    override fun check(userCredential: UserCredential): Boolean {
        return (userCredential.userMail.contains("@") && (userCredential.password.length > 6))
    }

}