package cl.cdum.therickandmorty.ui.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.cdum.therickandmorty.api.character.CharacterDetailData
import cl.cdum.therickandmorty.data.repository.CharacterRepository
import cl.cdum.therickandmorty.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(private val repository: CharacterRepository) :
    ViewModel() {

    private val mutableLiveData = MutableLiveData<Resource<CharacterDetailData>>()
    val liveListData: LiveData<Resource<CharacterDetailData>> = mutableLiveData

    fun getCharacterDetail(id: Int) =
        viewModelScope.launch {
            repository.getCharacterDetail(id).collect {
                mutableLiveData.value = it
            }
        }
}