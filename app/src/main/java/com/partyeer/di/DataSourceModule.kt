package com.partyeer.di

import com.partyeer.data.remote.store.login.remote.datasource.LoginDataSource
import com.partyeer.data.remote.store.login.remote.datasource.LoginRemoteDataSource
import com.partyeer.data.remote.store.party.remote.datasource.PartyDataSource
import com.partyeer.data.remote.store.party.remote.datasource.PartyRemoteDataSource
import com.partyeer.data.remote.store.user.remote.datasource.UserDataSource
import com.partyeer.data.remote.store.user.remote.datasource.UserRemoteDataSource
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

    @Singleton
    @Binds
    fun bindsLoginDataSource(dataSource: LoginRemoteDataSource): LoginDataSource

    @Singleton
    @Binds
    fun bindsUserDataSource(dataSource: UserRemoteDataSource): UserDataSource
}