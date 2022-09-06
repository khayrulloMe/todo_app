package uz.gita.node_app_from_khayrullo.view_model.impl

import androidx.lifecycle.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.node_app_from_khayrullo.data.room.entity.NoteEntity
import uz.gita.node_app_from_khayrullo.repository.impl.RepositoryImpl
import uz.gita.node_app_from_khayrullo.view_model.SearchNoteScreenViewModel

class SearchNoteScreenViewModelImpl : SearchNoteScreenViewModel, ViewModel() {
    private val repository = RepositoryImpl
    override val openBackStack: MutableSharedFlow<Unit> = MutableSharedFlow()
    override val searchedPage: MutableStateFlow<List<NoteEntity>> = MutableStateFlow(emptyList())

    init {
        viewModelScope.launch {
            repository.getAllNotes().collectLatest {
                searchedPage.emit(it)
            }
        }
    }

    override fun searcher(query: String) {
        viewModelScope.launch {
            repository.search(query).collectLatest{
                searchedPage.emit(it)
            }
        }
    }

    override fun back() {
      viewModelScope.launch {
          openBackStack.emit(Unit)
      }
    }
}