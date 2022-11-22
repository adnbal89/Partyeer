package com.partyeer.domain.repository.login.usecase

import com.google.firebase.auth.FirebaseUser
import com.partyeer.domain.repository.base.usecase.CoroutineUseCase
import com.partyeer.domain.repository.login.LoginRepository
import com.partyeer.domain.repository.login.model.UserCredential
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CheckUserAuthenticated @Inject constructor(
    private val repository: LoginRepository
) : CoroutineUseCase<UserCredential, Flow<FirebaseUser?>>() {

    override suspend fun buildUseCase(params: UserCredential?): Flow<FirebaseUser?> =
        repository.isUserValid(params!!)
}