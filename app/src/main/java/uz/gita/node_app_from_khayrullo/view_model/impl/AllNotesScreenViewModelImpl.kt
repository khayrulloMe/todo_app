package uz.gita.node_app_from_khayrullo.view_model.impl

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.launch
import uz.gita.node_app_from_khayrullo.data.room.entity.NoteEntity
import uz.gita.node_app_from_khayrullo.repository.impl.RepositoryImpl
import uz.gita.node_app_from_khayrullo.view_model.AllNotesScreenViewModel

class AllNotesScreenViewModelImpl : AllNotesScreenViewModel, ViewModel() {
    private val repository = RepositoryImpl
    override val allNote: MutableStateFlow<List<NoteEntity>> = MutableStateFlow(emptyList())

    init {
        viewModelScope.launch {
            repository.getAllNotes().collect {
                allNote.emit(it)
            }
        }
    }
}