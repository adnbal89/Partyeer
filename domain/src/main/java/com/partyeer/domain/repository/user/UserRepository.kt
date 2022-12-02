package com.partyeer.domain.repository.user

import com.partyeer.domain.repository.base.BaseRepository
import com.partyeer.domain.repository.user.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository : BaseRepository {

    suspend fun getUserDetailsByUserName(userName: String): Flow<User?>
}