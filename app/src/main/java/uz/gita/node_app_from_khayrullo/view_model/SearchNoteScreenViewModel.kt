package uz.gita.node_app_from_khayrullo.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import uz.gita.node_app_from_khayrullo.data.room.entity.NoteEntity

interface SearchNoteScreenViewModel {
    val searchedPage: SharedFlow<List<NoteEntity>>
    fun searcher(query: String)
    val openBackStack:Flow<Unit>
    fun back()
}