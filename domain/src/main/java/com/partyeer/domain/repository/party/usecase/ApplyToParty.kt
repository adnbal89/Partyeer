package com.partyeer.domain.repository.party.usecase

import com.partyeer.domain.repository.base.usecase.CoroutineUseCase
import com.partyeer.domain.repository.party.PartyRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApplyToParty @Inject constructor(
    private val repository: PartyRepository
) : CoroutineUseCase<ApplyToParty.Params, Unit>() {

    override suspend fun buildUseCase(params: Params?) =
        repository.applyToParty(params?.partyId!!, params?.userName!!)

    data class Params(
        val partyId: String,
        val userName: String
    )

}