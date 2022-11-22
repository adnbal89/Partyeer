package com.partyeer.data.remote.store.login.remote.service

import com.google.firebase.auth.FirebaseUser
import com.partyeer.data.remote.RemoteService
import com.partyeer.data.remote.store.login.model.UserCredentialDTO
import kotlinx.coroutines.flow.Flow

interface LoginRemoteService : RemoteService {

    suspend fun isUserValid(userCredentialDTO: UserCredentialDTO): Flow<FirebaseUser?>

}