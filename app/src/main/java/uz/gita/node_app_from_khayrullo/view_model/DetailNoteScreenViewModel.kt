package uz.gita.node_app_from_khayrullo.view_model

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import uz.gita.node_app_from_khayrullo.data.room.entity.NoteEntity

interface DetailNoteScreenViewModel {
    val backButton: Flow<Unit>
    val openEditNoteButton: Flow<Unit>
    val openDeleteAndShareNoteButton: Flow<Unit>
    fun deleteAndShareNoteButton()
    fun editNoteButton()
    fun back()
    fun delete(noteEntity: NoteEntity)

}