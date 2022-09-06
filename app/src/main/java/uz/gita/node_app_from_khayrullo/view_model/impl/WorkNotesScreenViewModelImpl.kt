package uz.gita.node_app_from_khayrullo.view_model.impl

import androidx.lifecycle.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import uz.gita.node_app_from_khayrullo.data.room.entity.NoteEntity
import uz.gita.node_app_from_khayrullo.repository.impl.RepositoryImpl
import uz.gita.node_app_from_khayrullo.view_model.WorkNotesScreenViewModel

class WorkNotesScreenViewModelImpl : WorkNotesScreenViewModel, ViewModel() {
    private val repository = RepositoryImpl

    override val workNoteFlow: MutableStateFlow<List<NoteEntity>> = MutableStateFlow(emptyList())

    init {
        viewModelScope.launch {
            repository.getWorkNote().collect {
                workNoteFlow.emit(it)
            }
        }
    }


}