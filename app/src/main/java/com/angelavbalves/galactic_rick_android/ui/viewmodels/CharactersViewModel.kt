package com.angelavbalves.galactic_rick_android.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.angelavbalves.galactic_rick_android.commons.utils.RequestResult
import com.angelavbalves.galactic_rick_android.data.repositories.CharactersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import flows.home.domain.models.characters.data.CharactersResultsResponse
import flows.home.domain.models.characters.domain.CharacterResult
import flows.home.presentation.ui.mappers.toPresentation
import flows.home.presentation.ui.models.CharacterHomePresentation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val charactersRepository: CharactersRepository
) : ViewModel() {

    fun fetchCharacters(): LiveData<RequestResult<List<CharacterHomePresentation>>> {
        val liveData = MutableLiveData<RequestResult<List<CharacterHomePresentation>>>()

        liveData.postValue(RequestResult.Loading)

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = charactersRepository.fetchCharacters().map {
                    it.toPresentation()
                }
                liveData.postValue(RequestResult.Success(result))
            } catch (e: Exception) {
                liveData.postValue(RequestResult.Error(e))
            }
        }
        return liveData
    }
}
