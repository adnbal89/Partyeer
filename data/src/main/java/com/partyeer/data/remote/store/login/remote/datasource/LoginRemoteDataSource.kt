package com.partyeer.data.remote.store.login.remote.datasource

import com.google.firebase.auth.FirebaseUser
import com.partyeer.data.remote.store.login.model.UserCredentialDTO
import com.partyeer.data.remote.store.login.remote.service.LoginRemoteService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginRemoteDataSource @Inject constructor(
    private val loginRemoteService: LoginRemoteService
) : LoginDataSource {

    override suspend fun isUserValid(userCredentialDTO: UserCredentialDTO): Flow<FirebaseUser?> =
        loginRemoteService.isUserValid(userCredentialDTO)

    override suspend fun createUserWithEmail(userCredentialDTO: UserCredentialDTO): Flow<FirebaseUser?> =
        loginRemoteService.createUserWithEmail(userCredentialDTO)

}