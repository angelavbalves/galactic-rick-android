package flows.home.domain.models.characters.domain

data class CharacterResult(
    val id: String,
    val characterName: String,
    val status: CharacterStatus,
    val specie: String,
    val gender: String,
    val origin: CharacterOrigin,
    val imageUrl: String,
    val episodes: List<String>
)

data class CharacterOrigin(
    val originName: String
)

enum class CharacterStatus(val status: String) {
    Alive("Alive"),
    Dead("Dead"),
    Unknown("Unknown")
}