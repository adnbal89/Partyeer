package com.partyeer.data.remote.store.user.remote.datasource

import com.partyeer.data.remote.store.user.remote.model.UserDTO
import com.partyeer.data.remote.store.user.remote.service.UserRemoteService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val userRemoteService: UserRemoteService
) : UserDataSource {
    override suspend fun getUserDetailsByUserName(userName: String): Flow<UserDTO?> {
        return userRemoteService.getUserDetailsByUserName(userName)
    }
}