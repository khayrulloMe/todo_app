package uz.gita.node_app_from_khayrullo.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow
import uz.gita.node_app_from_khayrullo.data.room.entity.NoteEntity

@Dao
interface NoteDao {

    @Insert
    suspend fun insert(noteEntity: NoteEntity)

    @Delete
    suspend fun delete(noteEntity: NoteEntity)

    @Update
    suspend fun update(noteEntity: NoteEntity)

    @Query("SELECT * FROM notes")
    fun getAllNotes(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM notes where label =1")
    fun getPersonal(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM notes where label = 2")
    fun getWork(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM notes where label = 3")
    fun getStudy(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM notes where title LIKE '%'||:search||'%'")
    fun search(search: String): Flow<List<NoteEntity>>

}