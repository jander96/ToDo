package todo.framework.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import todo.domain.usescases.GetAllProjectUC
import todo.domain.usescases.GetAllTaskUC
import todo.domain.usescases.SearchTaskUC
import todo.framework.Project
import todo.framework.Task
import todo.framework.ui.ScreenState
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor (
    private val getActiveTaskUC: GetAllTaskUC,
    private val searchTask: SearchTaskUC,
    private val getAllProject: GetAllProjectUC
): ViewModel() {


    private var _listaTask= MutableStateFlow<List<Task>>(emptyList())
    val listaTask:StateFlow<List<Task>> get() = _listaTask

    private var _screenState = MutableStateFlow(ScreenState.LOADING)
    val screenState: StateFlow<ScreenState> get()= _screenState

    private var _queryAswer = MutableStateFlow<List<Task>>(emptyList())
    val queryAswer: StateFlow<List<Task>> get() = _queryAswer

    private var _query = MutableStateFlow("")
    val query : StateFlow<String> get()= _query

    private var _allProjectList = MutableStateFlow<List<Project>>(emptyList())
    val allProjectList : StateFlow<List<Project>> get() = _allProjectList

    init{
        Log.d("viewModel","View Model Bien Ijectado")
        viewModelScope.launch(Dispatchers.Main) {
            getAllTaskInProject().collect{
                _listaTask.value= it
                setScreenState()
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            getAllProject.getAllProjects().collect{
                _allProjectList.value = it
            }
        }

    }


    private suspend fun getAllTaskInProject(): Flow<List<Task>> {
       return  getActiveTaskUC.getAllTasks()
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
        if(_listaTask.value.isEmpty()){
            _screenState.value= ScreenState.FAIL
        }else{
            _screenState.value = ScreenState.SUSCCES
        }
    }

}
