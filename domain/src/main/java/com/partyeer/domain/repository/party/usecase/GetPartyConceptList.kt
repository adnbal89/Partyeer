package com.partyeer.domain.repository.party.usecase

import com.partyeer.domain.repository.base.usecase.CoroutineUseCase
import com.partyeer.domain.repository.party.PartyRepository
import com.partyeer.domain.repository.party.model.Concept
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPartyConceptList @Inject constructor(
    val repository: PartyRepository
) : CoroutineUseCase<Unit, Flow<List<Concept>>>() {

    override suspend fun buildUseCase(params: Unit?) = repository.getPartyConcepts()
}