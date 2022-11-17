package com.partyeer.domain.repository.party.usecase

import com.partyeer.domain.repository.base.usecase.CoroutineUseCase
import com.partyeer.domain.repository.party.PartyRepository
import com.partyeer.domain.repository.party.model.Party
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetPartiesTaggedBy @Inject constructor(
    private val repository: PartyRepository
) : CoroutineUseCase<String, Flow<List<Party>>>() {
    override suspend fun buildUseCase(params: String?): Flow<List<Party>> {
        return repository.getPartiesTaggedBy(params!!)
    }
}