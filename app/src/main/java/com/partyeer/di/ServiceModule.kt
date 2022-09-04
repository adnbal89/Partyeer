package com.partyeer.di

import com.partyeer.data.remote.net.ApiProvider
import com.partyeer.data.remote.store.party.remote.PartyService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    //TODO
    @Singleton
    @Provides
    fun apiProvider(): ApiProvider =
        ApiProvider("https://reqres.in/api/")


    @Singleton
    @Provides
    fun providePartyService(apiProvider: ApiProvider): PartyService =
        apiProvider.create(PartyService::class.java)

}