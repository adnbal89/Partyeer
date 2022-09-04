package com.partyeer.di

import com.partyeer.data.remote.store.party.PartyDataSource
import com.partyeer.data.remote.store.party.remote.PartyRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Singleton
    @Binds
    fun bindsPartyDataSource(dataSource: PartyRemoteDataSource): PartyDataSource
}