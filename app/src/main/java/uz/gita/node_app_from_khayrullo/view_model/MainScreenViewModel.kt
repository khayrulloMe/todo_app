package uz.gita.node_app_from_khayrullo.view_model

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

interface MainScreenViewModel {
    val addButtonLiveData:Flow<Unit>
    val searchButtonLiveData:Flow<Unit>
    fun addButtonClicked()
    fun searchButtonClicked()

}