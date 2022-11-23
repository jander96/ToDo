package todo.framework.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import todo.domain.usescases.GetAllTaskUC
import todo.domain.usescases.SearchTaskUC
import todo.framework.Task
import todo.framework.ui.ScreenState
import javax.inject.Inject

@HiltViewModel
class InboxViewModel
@Inject constructor(
    private val getAllTaskUC: GetAllTaskUC,
    private val searchTask: SearchTaskUC
) : ViewModel() {

    private var _listOfInboxTask = MutableStateFlow<List<Task>>(emptyList())
    val listOfInboxTask: StateFlow<List<Task>> get() = _listOfInboxTask

    private var _queryAswer = MutableStateFlow<List<Task>>(emptyList())
    val queryAswer: StateFlow<List<Task>> get() = _queryAswer

    private var _query = MutableStateFlow("")
    val query : StateFlow<String> get()= _query

    private var _screenState = MutableStateFlow(ScreenState.LOADING)
    val screenState: StateFlow<ScreenState> get()= _screenState

    init {
        viewModelScope.launch(Dispatchers.IO) {

            getAllTaskUC.getAllTasks().collect {
                _listOfInboxTask.value = it
                    //it.filter { task -> task.projectId == null || task.projectId == "" }
                setScreenState()
            }
        }



    }
     fun searchTask(query:String){
         _query.value = query

         viewModelScope.launch(Dispatchers.IO) {
             searchTask.searchTask(_query.value).distinctUntilChanged().collect{
                 _queryAswer.value = it
             }
         }
     }

    private fun setScreenState(){
        Log.d("ScreenState","Se esta seteando el estado de la pantalla")
        _screenState.value = ScreenState.LOADING
       if(_listOfInboxTask.value.isEmpty()){
           _screenState.value= ScreenState.FAIL
       }else{
           _screenState.value = ScreenState.SUSCCES
       }
    }


}