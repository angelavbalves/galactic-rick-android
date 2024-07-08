package flows.home.domain.models.characters.data

import com.google.gson.annotations.SerializedName

data class CharactersResponse(
    val info: CharactersPaginationResponse?,
    @SerializedName("results")
    val characters: List<CharactersResultsResponse>
)
