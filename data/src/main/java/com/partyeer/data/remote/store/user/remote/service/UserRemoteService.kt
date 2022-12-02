package com.partyeer.data.remote.store.user.remote.service

import com.partyeer.data.remote.RemoteService
import com.partyeer.data.remote.store.user.remote.model.UserDTO
import kotlinx.coroutines.flow.Flow

interface UserRemoteService : RemoteService {

    suspend fun getUserDetailsByUserName(userName: String): Flow<UserDTO?>
}