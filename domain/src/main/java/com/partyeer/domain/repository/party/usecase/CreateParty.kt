package com.partyeer.domain.repository.party.usecase

import com.partyeer.domain.repository.base.usecase.CoroutineUseCase
import com.partyeer.domain.repository.party.PartyRepository
import com.partyeer.domain.repository.party.model.Party
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CreateParty @Inject constructor(
    private val repository: PartyRepository
) : CoroutineUseCase<Party, Unit>() {

    override suspend fun buildUseCase(params: Party?) {
        return repository.createParty(params!!)
    }
}