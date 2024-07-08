package com.angelavbalves.galactic_rick_android.ui.mappers

import com.angelavbalves.galactic_rick_android.data.repositories.CharactersRepository
import com.angelavbalves.galactic_rick_android.ui.models.CharacterDetailsPresentation
import flows.home.domain.models.characters.domain.CharacterResult
import flows.home.presentation.ui.mappers.toPresentation


suspend fun CharacterResult.toDetailsPresentation() : CharacterDetailsPresentation {
    return CharacterDetailsPresentation(
        id = id,
        characterName = characterName,
        status = status.toPresentation(),
        imageUrl = imageUrl,
        gender = gender,
        specie = specie,
        locationName = origin.originName,
    )
}

