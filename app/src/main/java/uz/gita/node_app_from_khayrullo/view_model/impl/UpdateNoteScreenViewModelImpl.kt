package uz.gita.node_app_from_khayrullo.view_model.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import uz.gita.node_app_from_khayrullo.data.room.entity.NoteEntity
import uz.gita.node_app_from_khayrullo.repository.impl.RepositoryImpl
import uz.gita.node_app_from_khayrullo.view_model.UpdateNoteScreenViewModel

class UpdateNoteScreenViewModelImpl : UpdateNoteScreenViewModel, ViewModel() {
    val repository = RepositoryImpl
    override val backButton: MutableSharedFlow<Unit> = MutableSharedFlow()


    override fun back() {
      viewModelScope.launch {
          backButton.emit(Unit)
      }
    }

    override fun delete(noteEntity: NoteEntity) {
viewModelScope.launch {
    repository.delete(noteEntity)
}
    }

    override fun update(noteEntity: NoteEntity) {
        viewModelScope.launch {
            repository.updateNote(noteEntity)
        }
    }
}