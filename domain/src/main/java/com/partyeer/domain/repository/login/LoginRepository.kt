package com.partyeer.domain.repository.login

import com.google.firebase.auth.FirebaseUser
import com.partyeer.domain.repository.base.BaseRepository
import com.partyeer.domain.repository.login.model.UserCredential
import kotlinx.coroutines.flow.Flow

interface LoginRepository : BaseRepository {

    suspend fun isUserValid(userCredential: UserCredential): Flow<FirebaseUser?>
}