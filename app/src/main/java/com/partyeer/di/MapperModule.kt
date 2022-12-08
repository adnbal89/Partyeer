package com.partyeer.di

import com.partyeer.data.remote.store.login.mapper.UserCredentialDTOMapper
import com.partyeer.data.remote.store.login.mapper.UserCredentialMapper
import com.partyeer.data.remote.store.party.mapper.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @Singleton
    @Provides
    fun provideConceptMapper(): ConceptMapper =
        ConceptMapper()

    @Singleton
    @Provides
    fun providePictureMapper(): PictureMapper =
        PictureMapper()

    @Singleton
    @Provides
    fun provideConceptDTOMapper(): ConceptDTOMapper =
        ConceptDTOMapper()

    @Singleton
    @Provides
    fun providePictureDTOMapper(): PictureDTOMapper =
        PictureDTOMapper()

    @Singleton
    @Provides
    fun provideUserCredentialDTOMapper(): UserCredentialDTOMapper =
        UserCredentialDTOMapper()

    @Singleton
    @Provides
    fun provideUserCredentialMapper(): UserCredentialMapper =
        UserCredentialMapper()

    @Singleton
    @Provides
    fun provideAddressDTOMapper(): AddressDTOMapper =
        AddressDTOMapper()

    @Singleton
    @Provides
    fun provideAddressMapper(): AddressMapper =
        AddressMapper()

}