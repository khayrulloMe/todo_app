package uz.gita.node_app_from_khayrullo.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import kotlinx.coroutines.flow.Flow
import uz.gita.node_app_from_khayrullo.data.room.entity.NoteEntity

interface StudyNoteScreenViewModel {
    val studyNoteFlow: Flow<List<NoteEntity>>
}