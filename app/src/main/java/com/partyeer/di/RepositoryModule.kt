package com.partyeer.di

import com.partyeer.data.LoginDataRepository
import com.partyeer.data.PartyDataRepository
import com.partyeer.data.remote.store.login.remote.service.FirebaseRemoteLoginService
import com.partyeer.data.remote.store.login.remote.service.LoginRemoteService
import com.partyeer.data.remote.store.party.remote.service.FirebaseRemotePartyService
import com.partyeer.data.remote.store.party.remote.service.PartyRemoteService
import com.partyeer.domain.repository.login.LoginRepository
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

    @Singleton
    @Binds
    fun bindLoginRepository(repository: LoginDataRepository): LoginRepository


    @Singleton
    @Binds
    fun bindPartyRemoteService(remoteService: FirebaseRemotePartyService): PartyRemoteService

    @Singleton
    @Binds
    fun bindLoginRemoteService(remoteService: FirebaseRemoteLoginService): LoginRemoteService

}