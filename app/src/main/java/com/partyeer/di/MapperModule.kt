package com.partyeer.di

import com.partyeer.data.remote.store.party.mapper.ConceptDTOMapper
import com.partyeer.data.remote.store.party.mapper.ConceptMapper
import com.partyeer.data.remote.store.party.mapper.PictureDTOMapper
import com.partyeer.data.remote.store.party.mapper.PictureMapper
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

}