package uz.gita.node_app_from_khayrullo.repository.impl

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import uz.gita.node_app_from_khayrullo.data.room.app_database.AppDatabase
import uz.gita.node_app_from_khayrullo.data.room.entity.NoteEntity
import uz.gita.node_app_from_khayrullo.repository.Repository

object RepositoryImpl : Repository {
    private val dao = AppDatabase.getInstance().dao()
    override suspend fun addNote(noteEntity: NoteEntity) {
        withContext(Dispatchers.IO) {
            dao.insert(noteEntity)
        }
    }

    override suspend fun updateNote(noteEntity: NoteEntity) {
        withContext(Dispatchers.IO) {
            dao.update(noteEntity)
        }
    }

    override suspend fun delete(noteEntity: NoteEntity) {
        withContext(Dispatchers.IO) {
            dao.delete(noteEntity)
        }
    }

    override fun getAllNotes() = flow {
        emitAll(dao.getAllNotes())
    }.flowOn(Dispatchers.IO)

    override fun getPersonalNote() = flow {
        emitAll(dao.getPersonal())

    }.flowOn(Dispatchers.IO)

    override fun getWorkNote() = flow {
        emitAll(dao.getWork())

    }.flowOn(Dispatchers.IO)

    override fun getStudyNote() = flow {
        emitAll(dao.getStudy())

    }.flowOn(Dispatchers.IO)

    override fun search(query: String) = flow {
        emitAll(dao.search(query))

    }

}