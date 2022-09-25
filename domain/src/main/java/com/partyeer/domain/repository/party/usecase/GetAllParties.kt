package com.partyeer.domain.repository.party.usecase

import com.partyeer.domain.repository.base.usecase.CoroutineUseCase
import com.partyeer.domain.repository.party.PartyRepository
import com.partyeer.domain.repository.party.model.Party
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAllParties @Inject constructor(
    private val repository: PartyRepository
) : CoroutineUseCase<Unit, Flow<List<Party>>>() {

    override suspend fun buildUseCase(params: Unit?): Flow<List<Party>> {
        return repository.getPartyList()
    }
}