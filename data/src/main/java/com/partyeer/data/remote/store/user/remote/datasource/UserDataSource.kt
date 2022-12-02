package com.partyeer.data.remote.store.user.remote.datasource

import com.partyeer.data.remote.store.user.remote.model.UserDTO
import kotlinx.coroutines.flow.Flow

interface UserDataSource {

    suspend fun getUserDetailsByUserName(userName: String): Flow<UserDTO?>
}