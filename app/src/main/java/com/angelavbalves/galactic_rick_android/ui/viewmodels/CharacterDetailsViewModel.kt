package com.angelavbalves.galactic_rick_android.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.angelavbalves.galactic_rick_android.commons.utils.RequestResult
import com.angelavbalves.galactic_rick_android.data.repositories.CharactersRepository
import com.angelavbalves.galactic_rick_android.ui.mappers.toDetailsPresentation
import com.angelavbalves.galactic_rick_android.ui.models.CharacterDetailsPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import flows.home.presentation.ui.mappers.toPresentation
import flows.home.presentation.ui.models.CharacterHomePresentation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val charactersRepository: CharactersRepository,
) : ViewModel() {

    fun fetchCharacter(id: Int): LiveData<RequestResult<CharacterDetailsPresentation>> {
        val liveData = MutableLiveData<RequestResult<CharacterDetailsPresentation>>()

        liveData.postValue(RequestResult.Loading)

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = charactersRepository.fetchCharacterDetails(id).toDetailsPresentation()
                liveData.postValue(RequestResult.Success(result))
            } catch (e: Exception) {
                liveData.postValue(RequestResult.Error(e))
            }
        }
        return liveData
    }
}