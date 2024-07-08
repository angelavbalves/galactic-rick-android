package com.angelavbalves.galactic_rick_android.data.datasources

import com.angelavbalves.galactic_rick_android.data.network.CharactersApi
import flows.home.domain.models.characters.data.CharactersResponse
import flows.home.domain.models.characters.data.CharactersResultsResponse
import javax.inject.Inject

class CharactersDataSource @Inject constructor(
    private val api: CharactersApi
) {
    suspend fun fetchCharacters(): CharactersResponse {
        return api.fetchCharacters()
    }

    suspend fun fetchCharacterDetails(id: Int) : CharactersResultsResponse {
        return api.fetchCharacterDetails(id = id)
    }
}