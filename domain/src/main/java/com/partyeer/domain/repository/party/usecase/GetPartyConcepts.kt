package com.partyeer.domain.repository.party.usecase

import com.partyeer.domain.repository.base.usecase.CoroutineUseCase
import com.partyeer.domain.repository.party.model.Concept
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetPartyConcepts @Inject constructor(

) : CoroutineUseCase<Unit, Flow<List<Concept>>>() {
    override suspend fun buildUseCase(params: Unit?): Flow<List<Concept>> {
        TODO("Not yet implemented")
    }
}