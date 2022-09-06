package uz.gita.node_app_from_khayrullo.view_model

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import uz.gita.node_app_from_khayrullo.data.room.entity.NoteEntity

interface UpdateNoteScreenViewModel {
    val backButton: Flow<Unit>
    fun back()
    fun delete(noteEntity:NoteEntity)
    fun update(noteEntity: NoteEntity)
}
