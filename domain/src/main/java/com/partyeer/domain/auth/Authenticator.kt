package com.partyeer.domain.auth

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Authenticator @Inject constructor() {

    fun userLoggedIn() = true
}