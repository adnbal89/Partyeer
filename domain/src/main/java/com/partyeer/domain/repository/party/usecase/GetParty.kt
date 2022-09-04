package com.partyeer.domain.repository.party.usecase

import com.partyeer.domain.repository.base.usecase.CoroutineUseCase
import com.partyeer.domain.repository.party.PartyRepository
import com.partyeer.domain.repository.party.model.Party
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetParty @Inject constructor(
    private val repository: PartyRepository
) : CoroutineUseCase<GetParty.Params, Party>() {

    data class Params(
        val id: String
    )

    override suspend fun buildUseCase(params: Params?): Party {
        return repository.getParty(params!!.id)
    }
}