package uz.gita.node_app_from_khayrullo.repository

import androidx.lifecycle.LiveData
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import uz.gita.node_app_from_khayrullo.data.room.entity.NoteEntity

interface Repository {
    suspend fun addNote(noteEntity: NoteEntity)
    suspend fun updateNote(noteEntity: NoteEntity)
    suspend fun delete(noteEntity: NoteEntity)
     fun getAllNotes(): Flow<List<NoteEntity>>
     fun getPersonalNote(): Flow<List<NoteEntity>>
     fun getWorkNote(): Flow<List<NoteEntity>>
     fun getStudyNote(): Flow<List<NoteEntity>>
     fun search(query: String): Flow<List<NoteEntity>>

}