package com.partyeer.di

import com.partyeer.data.remote.store.party.PartyDataRepository
import com.partyeer.domain.repository.party.PartyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Singleton
    @Binds
    fun bindPartyRepository(repository: PartyDataRepository): PartyRepository
}