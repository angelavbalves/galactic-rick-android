package com.angelavbalves.galactic_rick_android.data.repositories

import com.angelavbalves.galactic_rick_android.data.datasources.CharactersDataSource
import flows.home.domain.mappers.toDomain
import flows.home.domain.models.characters.domain.CharacterResult
import javax.inject.Inject

class CharactersRepository @Inject constructor(
    private val charactersDataSource: CharactersDataSource
) {
    suspend fun fetchCharacters(): List<CharacterResult> {
        return charactersDataSource
            .fetchCharacters()
            .characters
            .map { it.toDomain() }

    }

    suspend fun fetchCharacterDetails(id: Int): CharacterResult {
        return charactersDataSource
            .fetchCharacterDetails(id)
            .toDomain()
    }
}