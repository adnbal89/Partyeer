package com.partyeer.presentation.ui.main.util.checker

import com.partyeer.domain.repository.login.model.UserCredential

interface UserCredentialChecker : Checker {
    fun check(userCredential: UserCredential): Boolean
}