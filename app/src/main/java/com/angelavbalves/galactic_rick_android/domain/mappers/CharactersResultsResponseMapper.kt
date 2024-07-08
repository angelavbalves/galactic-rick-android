package flows.home.domain.mappers

import com.angelavbalves.galactic_rick_android.domain.mappers.toDomain
import flows.home.domain.models.characters.data.CharactersResultsResponse
import flows.home.domain.models.characters.domain.CharacterResult
import flows.home.domain.models.characters.domain.CharacterStatus


fun CharactersResultsResponse.toDomain() : CharacterResult {
    return CharacterResult(
        id = id,
        characterName = characterName,
        status = status?.toDomain() ?: CharacterStatus.Unknown,
        specie = specie,
        gender = gender,
        origin = origin.toDomain(),
        imageUrl = imageUrl,
        episodes = episodes
    )
}
