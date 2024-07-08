package com.angelavbalves.galactic_rick_android.data.di

import com.angelavbalves.galactic_rick_android.data.datasources.CharactersDataSource
import com.angelavbalves.galactic_rick_android.data.network.CharactersApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun providesDataSourceModule(
        api: CharactersApi
    ): CharactersDataSource {
        return CharactersDataSource(api)
    }
}