package com.angelavbalves.galactic_rick_android.domain.mappers

import flows.home.domain.models.characters.data.CharacterStatusResponse
import flows.home.domain.models.characters.domain.CharacterStatus


fun CharacterStatusResponse.toDomain() : CharacterStatus {
    return when (this) {
        CharacterStatusResponse.Alive -> CharacterStatus.Alive
        CharacterStatusResponse.Dead -> CharacterStatus.Dead
        CharacterStatusResponse.Unknown -> CharacterStatus.Unknown
    }
}