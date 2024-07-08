package com.angelavbalves.galactic_rick_android.ui.models

import flows.home.presentation.ui.models.CharacterStatusPresentation


data class CharacterDetailsPresentation(
    val id: String,
    val characterName: String,
    val status: CharacterStatusPresentation,
    val imageUrl: String,
    val locationName: String,
    val gender: String,
    val specie: String,
)