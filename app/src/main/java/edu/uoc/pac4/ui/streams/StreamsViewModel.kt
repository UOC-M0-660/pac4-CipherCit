package edu.uoc.pac4.ui.streams

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.uoc.pac4.data.streams.Stream
import edu.uoc.pac4.data.streams.StreamsRepository
import kotlinx.coroutines.launch

class StreamsViewModel(
    private val repository: StreamsRepository
) : ViewModel() {

  // no declaro esta propiedad como observable porque no es necesario notificar su cambio
  var nextCursor: String? = null
  val streams = MutableLiveData<List<Stream>>()

  fun getStreams(cursor: String?){
    viewModelScope.launch {
      val retrievedValues = repository.getStreams(cursor)
      streams.postValue(retrievedValues.second)
      nextCursor = retrievedValues.first
    }
  }
}