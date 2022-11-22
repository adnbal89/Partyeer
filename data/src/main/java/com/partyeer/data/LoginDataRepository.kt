package com.partyeer.data

import com.google.firebase.auth.FirebaseUser
import com.partyeer.data.remote.store.login.mapper.UserCredentialDTOMapper
import com.partyeer.data.remote.store.login.remote.datasource.LoginDataSource
import com.partyeer.domain.repository.login.LoginRepository
import com.partyeer.domain.repository.login.model.UserCredential
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginDataRepository @Inject constructor(
    private val loginDataSource: LoginDataSource,
    private val userCredentialDTOMapper: UserCredentialDTOMapper,
) : LoginRepository {
    override suspend fun isUserValid(userCredential: UserCredential): Flow<FirebaseUser?> =
        loginDataSource.isUserValid(userCredentialDTOMapper.map(userCredential))
}