package flows.home.presentation.ui.models

data class CharacterHomePresentation(
    val id: String,
    val characterName: String,
    val status: CharacterStatusPresentation,
    val imageUrl: String,
    val episodes: String,
)

enum class CharacterStatusPresentation(val status: String) {
    Alive("Alive"),
    Dead("Dead"),
    Unknown("Unknown")
}