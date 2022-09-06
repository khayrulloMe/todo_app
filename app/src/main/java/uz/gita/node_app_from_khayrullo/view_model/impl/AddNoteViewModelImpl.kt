package uz.gita.node_app_from_khayrullo.view_model.impl

import androidx.lifecycle.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import uz.gita.node_app_from_khayrullo.data.room.entity.NoteEntity
import uz.gita.node_app_from_khayrullo.repository.impl.RepositoryImpl
import uz.gita.node_app_from_khayrullo.view_model.AddNoteViewModel

class AddNoteViewModelImpl() : AddNoteViewModel, ViewModel() {
    val repository = RepositoryImpl
    override val spinnerLiveData: MutableLiveData<String> = MutableLiveData()
    override val backButton: MutableSharedFlow<Unit> = MutableSharedFlow()
    override fun back() {
        viewModelScope.launch {
            backButton.emit(Unit)
        }
    }

    override fun add(noteEntity: NoteEntity) {
        viewModelScope.launch {
            repository.addNote(noteEntity)

        }
    }
}