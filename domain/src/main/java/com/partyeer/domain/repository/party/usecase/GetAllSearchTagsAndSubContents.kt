package com.partyeer.domain.repository.party.usecase

import com.partyeer.domain.repository.base.usecase.CoroutineUseCase
import com.partyeer.domain.repository.party.PartyRepository
import com.partyeer.domain.repository.party.model.Tag
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllSearchTagsAndSubContents @Inject constructor(
    private val repository: PartyRepository
) : CoroutineUseCase<Unit, Flow<List<Tag>>>() {
    override suspend fun buildUseCase(params: Unit?): Flow<List<Tag>> {
        return repository.getAllSearchTagsAndSubContents()
    }
}