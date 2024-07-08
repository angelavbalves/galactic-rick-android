package flows.home.domain.models.characters.data

import com.google.gson.annotations.SerializedName

data class CharactersResultsResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val characterName: String,
    @SerializedName("status")
    val status: CharacterStatusResponse? = CharacterStatusResponse.Unknown,
    @SerializedName("species")
    val specie: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("origin")
    val origin: CharacterOriginResponse,
    @SerializedName("image")
    val imageUrl: String,
    @SerializedName("episode")
    val episodes: List<String>
)

data class CharacterOriginResponse(
    @SerializedName("name")
    val originName: String
)

enum class CharacterStatusResponse(val status: String) {
    Alive("Alive"),
    Dead("Dead"),
    Unknown("Unknown")
}