package com.partyeer.data.remote.store.login.remote.datasource

import com.google.firebase.auth.FirebaseUser
import com.partyeer.data.remote.store.login.model.UserCredentialDTO
import kotlinx.coroutines.flow.Flow

interface LoginDataSource {
    suspend fun isUserValid(userCredentialDTO: UserCredentialDTO): Flow<FirebaseUser?>
    suspend fun createUserWithEmail(userCredentialDTO: UserCredentialDTO): Flow<FirebaseUser?>
}