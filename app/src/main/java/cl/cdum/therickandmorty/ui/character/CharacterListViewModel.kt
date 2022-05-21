package cl.cdum.therickandmorty.ui.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.cdum.therickandmorty.api.character.CharacterListData
import cl.cdum.therickandmorty.data.repository.CharacterRepository
import cl.cdum.therickandmorty.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(private val repository: CharacterRepository) :
    ViewModel() {

    private val mutableLiveData = MutableLiveData<Resource<CharacterListData>>()
    val liveListData: LiveData<Resource<CharacterListData>> = mutableLiveData

    fun getCharacterList() =
        viewModelScope.launch {
            repository.getCharacterList().collect {
                mutableLiveData.value = it
            }
        }

    /**
     * Events section
     */

    private val eventChannel = Channel<CharacterEvent>()
    val event = eventChannel.receiveAsFlow()

    fun onItemSelected(identifier: Int) = viewModelScope.launch {
        eventChannel.send(CharacterEvent.NavigateToDetailScreen(identifier))
    }
}