package com.partyeer.domain.repository.party.usecase

import com.partyeer.domain.repository.base.usecase.CoroutineUseCase
import com.partyeer.domain.repository.party.PartyRepository
import javax.inject.Inject

class ApplyToParty @Inject constructor(
    private val repository: PartyRepository
) : CoroutineUseCase<String, Unit>() {

    override suspend fun buildUseCase(params: String?) =
        repository.applyToParty(params!!)

    data class Params(
        val partyId: String
    )
}