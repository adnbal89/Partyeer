package com.partyeer.di

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.partyeer.data.remote.net.ApiProvider
import com.partyeer.data.remote.store.party.remote.PartyRemoteService
import com.partyeer.util.formatter.FileNameFormatter
import dagger.Binds
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


   /* @Singleton
    @Provides
    fun providePartyRemoteService(apiProvider: ApiProvider): PartyRemoteService =
        apiProvider.create(PartyRemoteService::class.java)*/


    @Singleton
    @Provides
    fun provideFirebaseDatabaseReference() = Firebase.database.getReference("party")

    @Singleton
    @Provides
    fun provideFirebaseStorageReference() =
        Firebase.storage.getReference("images/${FileNameFormatter.getFormattedFileName()}")

}