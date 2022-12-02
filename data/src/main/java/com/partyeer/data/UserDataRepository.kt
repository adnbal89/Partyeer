package com.partyeer.data

import com.partyeer.data.remote.store.user.mapper.UserMapper
import com.partyeer.data.remote.store.user.remote.datasource.UserDataSource
import com.partyeer.domain.repository.user.model.User
import com.partyeer.domain.repository.user.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserDataRepository @Inject constructor(
    private val userDataSource: UserDataSource,
    private val userMapper: UserMapper
) : UserRepository {

    override suspend fun getUserDetailsByUserName(userName: String): Flow<User?> =
        userDataSource.getUserDetailsByUserName(userName).map {
            userMapper.map(it!!)
        }
}