package com.angelavbalves.galactic_rick_android.data.di

import com.angelavbalves.galactic_rick_android.data.datasources.CharactersDataSource
import com.angelavbalves.galactic_rick_android.data.repositories.CharactersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesCharactersRepository(
        charactersDataSource: CharactersDataSource
    ): CharactersRepository {
        return CharactersRepository(charactersDataSource)
    }
}