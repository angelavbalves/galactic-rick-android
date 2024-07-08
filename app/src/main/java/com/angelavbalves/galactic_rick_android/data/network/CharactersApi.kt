package com.angelavbalves.galactic_rick_android.data.network

import flows.home.domain.models.characters.data.CharactersResponse
import flows.home.domain.models.characters.data.CharactersResultsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CharactersApi {
    @GET("character")
    suspend fun fetchCharacters(): CharactersResponse

    @GET("character/{id}")
    suspend fun fetchCharacterDetails(
        @Path("id") id: Int
    ) : CharactersResultsResponse
}
