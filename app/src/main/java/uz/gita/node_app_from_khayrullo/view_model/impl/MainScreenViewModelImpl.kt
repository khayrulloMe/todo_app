package uz.gita.node_app_from_khayrullo.view_model.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import uz.gita.node_app_from_khayrullo.view_model.MainScreenViewModel

class MainScreenViewModelImpl : MainScreenViewModel,ViewModel() {
    override val addButtonLiveData: MutableSharedFlow<Unit> = MutableSharedFlow()
    override val searchButtonLiveData: MutableSharedFlow<Unit> = MutableSharedFlow()
    override fun addButtonClicked() {
   viewModelScope.launch {
       addButtonLiveData.emit(Unit)
   }
    }

    override fun searchButtonClicked() {
      viewModelScope.launch {
          searchButtonLiveData.emit(Unit)
      }
    }
}