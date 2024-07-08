package com.angelavbalves.galactic_rick_android.domain.mappers

import flows.home.domain.models.characters.data.CharacterOriginResponse
import flows.home.domain.models.characters.domain.CharacterOrigin

fun CharacterOriginResponse.toDomain() : CharacterOrigin {
    return CharacterOrigin(
        originName = originName
    )
}