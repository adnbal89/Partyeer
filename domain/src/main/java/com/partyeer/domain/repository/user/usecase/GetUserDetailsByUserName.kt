package com.partyeer.domain.repository.user.usecase

import com.partyeer.domain.repository.base.usecase.CoroutineUseCase
import com.partyeer.domain.repository.user.model.User
import com.partyeer.domain.repository.user.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserDetailsByUserName @Inject constructor(
    private val userRepository: UserRepository
) : CoroutineUseCase<String, Flow<User?>>() {

    override suspend fun buildUseCase(params: String?): Flow<User?> {
        println(params)
        return userRepository.getUserDetailsByUserName(params!!)
    }
}