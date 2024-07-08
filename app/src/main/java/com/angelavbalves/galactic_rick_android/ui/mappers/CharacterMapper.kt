package flows.home.presentation.ui.mappers

import flows.home.domain.models.characters.domain.CharacterResult
import flows.home.domain.models.characters.domain.CharacterStatus
import flows.home.presentation.ui.models.CharacterHomePresentation
import flows.home.presentation.ui.models.CharacterStatusPresentation


fun CharacterResult.toPresentation() : CharacterHomePresentation {
    return CharacterHomePresentation(
        id = id,
        characterName = characterName,
        status = status.toPresentation(),
        imageUrl = imageUrl,
        episodes = countEpisodes(episodes)
    )
}

fun CharacterStatus.toPresentation() : CharacterStatusPresentation {
    return when (this) {
        CharacterStatus.Alive -> CharacterStatusPresentation.Alive
        CharacterStatus.Dead -> CharacterStatusPresentation.Dead
        CharacterStatus.Unknown -> CharacterStatusPresentation.Unknown
    }
}

fun countEpisodes(episodes: List<String>) : String {
    val size = episodes.size
    return "$size episódios"
}


